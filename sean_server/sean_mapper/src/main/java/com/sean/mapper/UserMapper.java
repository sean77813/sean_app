package com.sean.mapper;

import com.sean.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    User selectByUid(String uid);

    User selectByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int deleteByUid(String uid);

    User loadUserByUsername(String username);

    List<User> getAllUsers(@Param("uid") String uid, @Param("keywords") String keywords);
}