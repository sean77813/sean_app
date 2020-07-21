package com.sean.mapper;

import com.sean.model.Menu;
import com.sean.model.MenuTemplate;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    int insertMenu(MenuTemplate menu);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenusByUserId(String uid);

    List<Menu> selectByPid(String pid);

    List<Menu> findMenuAll();

    String[] findMenusByRid(String rid);
}