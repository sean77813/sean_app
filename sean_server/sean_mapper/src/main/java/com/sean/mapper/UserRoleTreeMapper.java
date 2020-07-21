package com.sean.mapper;

import com.sean.model.UserRoleTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleTreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRoleTree record);

    int insertSelective(UserRoleTree record);

    UserRoleTree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleTree record);

    int updateByPrimaryKey(UserRoleTree record);

    String[] findRidsByUid(@Param("uid") String uid);

    int deleteUserRole(@Param("uid") String uid,@Param("rid") String rid);

    int addUserRole(@Param("id")String id,@Param("uid")String uid,@Param("rid") String rid);
}