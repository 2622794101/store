package com.lgm.store.mapper;

import com.lgm.store.entitly.User;

import java.util.Date;

public interface UserMapper {

    Integer insert(User user);

    User findByUsername(String username);

    User findByUid(Integer uid);

    /**
     * @param uid
     * @param password
     * @param modifiedUser 修改密码的执行者
     * @param modifiedTime 修改密码的时间
     * @return
     */
    Integer updatePasswordByUid(Integer uid
            , String password
            , String modifiedUser
            , Date modifiedTime);

    Integer updateUserData(Integer uid
            , String phone
            , String email
            , Integer gender
            , String modifiedUser
            , Date modifiedTime);

    Integer updateAvatarByUid(Integer uid
            , String avatar
            , String modifiedUser
            , Date modifiedTime);
}
