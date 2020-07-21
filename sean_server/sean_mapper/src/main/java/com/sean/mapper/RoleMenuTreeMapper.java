package com.sean.mapper;

import com.sean.model.RoleMenuTree;
import org.apache.ibatis.annotations.Param;


public interface RoleMenuTreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleMenuTree record);

    int insertSelective(RoleMenuTree record);

    RoleMenuTree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleMenuTree record);

    int updateByPrimaryKey(RoleMenuTree record);

    int deleteRoleMenu(@Param("rid")String rid,@Param("mid") String mid);

    int addRoleMenu(@Param("id") String id,@Param("rid") String rid,@Param("mid") String mid);
}