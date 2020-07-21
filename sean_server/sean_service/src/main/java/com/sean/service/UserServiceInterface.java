package com.sean.service;

import com.sean.model.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getAllUsers(String keywords);

    int updateUser(User user) throws Exception;

    boolean updateUserRole(String uid,String[] rids);

    int deleteUserById(String uid);

    int deleteUsers(String[] uids) throws Exception;

    int saveUser(User user) throws Exception;

    User findUserByUid(String uid);

    boolean isExist(String uid);


}
