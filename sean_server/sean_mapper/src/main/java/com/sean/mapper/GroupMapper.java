package com.sean.mapper;

import com.sean.model.Group;

public interface GroupMapper {
    int insert(Group record);

    int insertSelective(Group record);
}