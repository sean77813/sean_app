package com.sean.mapper;

import com.sean.model.UserGroupTree;

public interface UserGroupTreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserGroupTree record);

    int insertSelective(UserGroupTree record);

    UserGroupTree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserGroupTree record);

    int updateByPrimaryKey(UserGroupTree record);
}