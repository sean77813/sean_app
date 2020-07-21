package com.sean.service;

import com.sean.model.Menu;
import com.sean.model.MenuTemplate;

import java.util.List;

public interface MenuServiceInterface {

    List<Menu> findMenusByPid(String pid);

    String[] findMenusByRole(String rid);

    int saveRoleMenus(String[] menus, String rid);

    int addMenu(MenuTemplate menu);

    int editMenu(Menu menu);
}
