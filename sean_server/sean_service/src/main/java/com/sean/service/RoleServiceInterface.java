package com.sean.service;

import com.sean.model.Role;

import java.util.List;

public interface RoleServiceInterface {

    List<Role> getAllRoles();

    int updateUserRole(Role role);

    boolean isExist(String rid);

    int saveRole(Role role);

    int editRoleName(Role role);

    String[] findRolesByUid(String uid);
 }
