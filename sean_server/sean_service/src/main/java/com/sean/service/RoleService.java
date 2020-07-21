package com.sean.service;

import com.sean.mapper.RoleMapper;
import com.sean.mapper.UserRoleTreeMapper;
import com.sean.model.Role;
import com.sean.utils.StringTools;
import com.sean.utils.UserTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServiceInterface{

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleTreeMapper userRoleTreeMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public int updateUserRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public boolean isExist(String rid) {
        if(StringTools.isEmpty(rid))
            return false;
        Role role = roleMapper.selectByRid(rid);
        if( null!=role ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int saveRole(Role role) {
        role.setId(UserTools.getUUID());
        return roleMapper.insertSelective(role);
    }

    @Override
    public int editRoleName(Role role) {
        if(StringTools.isEmpty(role.getRname()))
            return 0;
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public String[] findRolesByUid(String uid) {
        if(StringTools.isEmpty(uid))
            return null;
        return userRoleTreeMapper.findRidsByUid(uid);
    }

}
