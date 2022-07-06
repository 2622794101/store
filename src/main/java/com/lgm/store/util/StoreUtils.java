package com.lgm.store.util;

import com.lgm.store.entitly.User;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class StoreUtils {
    public String getMd5Password(String password, String salt) {
        for (int i = 0; i <= 3; i++) {
            //md5加密算法
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    /**
     *  清除用户的非必要数据,仅保存 uid,username,avatar
     * @param user
     * @return
     */
    public User cleanUser(User user) {
        User resultUser = new User();
        resultUser.setUid(user.getUid());
        resultUser.setUsername(user.getUsername());
        resultUser.setAvatar(user.getAvatar());
        return resultUser;
    }
}
