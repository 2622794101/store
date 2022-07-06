package com.lgm.store.service.impl;

import com.lgm.store.entitly.User;
import com.lgm.store.mapper.UserMapper;
import com.lgm.store.service.IUserService;
import com.lgm.store.service.ex.*;
import com.lgm.store.service.ex.user.PasswordNotMatchException;
import com.lgm.store.service.ex.user.UsernameDuplicatedExeception;
import com.lgm.store.service.ex.user.UsernameNotFoundException;
import com.lgm.store.util.StoreUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private StoreUtils storeUtils;

    @Override
    public void register(User user) {
        //判断用户是否已存在
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if (result != null) {
            throw new UsernameDuplicatedExeception("用户名被占用");
        }

        //密码md5加密
        String password = user.getPassword();
        //获取盐值（随机）
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = storeUtils.getMd5Password(password, salt);
        user.setSalt(salt);
        user.setPassword(md5Password);

        //补全数据,4个日志和一个删除状态
        user.setIsDelete(0);
        user.setCreatedUser(user.getCreatedUser());
        user.setModifiedUser(user.getCreatedUser());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //插入用户数据
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }


    }

    @Override
    public User login(String username, String password) {
        //判断用户是否已存在
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不纯在");
        }
        //验证密码是否正确
        String md5Password = user.getPassword();
        String salt = user.getSalt();
        String newMd5Password = storeUtils.getMd5Password(password, salt);

        if (!md5Password.equals(newMd5Password)) {
            throw new PasswordNotMatchException("用户密码错误");
        }

        //判断用户是否被逻辑删除
        if (user.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户数据不纯在");
        }

        //清洗数据
        user = storeUtils.cleanUser(user);

        return user;
    }

    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword, String modifiedUser) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) {
            throw new UsernameNotFoundException("用户未找到！");
        }
        String salt = user.getSalt();
//        老密码加密操作
        String md5Password = storeUtils.getMd5Password(oldPassword, salt);
        if (!md5Password.equals(user.getPassword())) {
            throw new PasswordNotMatchException("密码错误");
        }

        md5Password = storeUtils.getMd5Password(newPassword, salt);
        Integer result = userMapper.updatePasswordByUid(uid, md5Password, modifiedUser, new Date());
        if (result != 1) {
            throw new UpdateException("更新时产生了未知异常");
        }
    }

    @Override
    public void changUserData(Integer uid, String phone, String email, Integer gender, String modifiedUser) {
        //查询一下是否有uid
        getUserByUid(uid);
        Integer integer = userMapper.updateUserData(uid, phone, email, gender, modifiedUser, new Date());

        if (integer != 1) {
            throw new UpdateException("更新时产生未知异常");
        }
    }

    @Override
    public User getUserByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) {
            throw new UsernameNotFoundException("未找到用户");
        }
        User resultUser = new User();
        resultUser.setUsername(user.getUsername());
        resultUser.setPhone(user.getPhone());
        resultUser.setEmail(user.getEmail());
        resultUser.setGender(user.getGender());
        return resultUser;
    }

    @Override
    public void changeAvatarByUid(Integer uid, String avatar, String modifiedUser) {
        getUserByUid(uid);
        Integer result = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, new Date());

        if (result != 1){
            throw new UpdateException("更新时产生未知异常！");
        }
    }


}
