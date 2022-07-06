package com.lgm.store.service;

import com.lgm.store.entitly.User;

public interface IUserService {
    void register(User user);

    User login(String username, String password);

    void changePassword(Integer uid
            , String oldPassword
            , String newPassword
            , String modifiedUser
    );

    void changUserData(Integer uid
            , String phone
            , String email
            , Integer gender
            , String modifiedUser);

    User getUserByUid(Integer uid);

    void changeAvatarByUid(Integer uid
    ,String avatar
    ,String modifiedUser);

}
