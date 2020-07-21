package com.sean.service;

import com.sean.exception.BaseException;
import com.sean.exception.UserException;
import com.sean.mapper.UserMapper;
import com.sean.mapper.UserRoleTreeMapper;
import com.sean.model.Role;
import com.sean.model.User;
import com.sean.utils.StringTools;
import com.sean.utils.UserTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin2.main.server.ResultID;

import javax.validation.constraints.Pattern;
import java.awt.event.TextEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService ,UserServiceInterface{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleTreeMapper userRoleTreeMapper;
    @Autowired
    private RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if ( user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId("1");
        role.setRid("ROLE_admin");
        role.setRname("系统管理员");
        roles.add(role);
        user.setRoles(roles);
        return user;
    }


    @Override
    public List<User> getAllUsers(String keywords) {
            return userMapper.getAllUsers(UserTools.getCurrentUser().getId(),keywords);
    }

    @Override
    public int updateUser(User user) throws Exception{

        String id = user.getId();
        String uid = user.getUid();
        String username = user.getUsername();
        User  temp = userMapper.selectByUid(uid);
        if( null!=temp){
            if ( null != temp.getId() &&  !id.equals(temp.getId())){
                throw new UserException.UidExisted("UID已存在！uid="+uid);
            }
        }
        User temp2 = userMapper.selectByUsername(username);
        if( null!=temp2 ){
            if ( null != temp2.getId() &&  !id.equals(temp2.getId()) ){
                throw new UserException.UserNameExisted("UserName已存在！username="+username);
            }
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    @Override
    public boolean updateUserRole(String uid, String[] rids) {
        if(StringTools.isEmpty(uid))
            return false;

        String[] oldRids = userRoleTreeMapper.findRidsByUid(uid);
        String[] minus = StringTools.minus(rids,oldRids);
        try {
            for(String rid: minus){
                if(Arrays.asList(oldRids).contains(rid)){
                    userRoleTreeMapper.deleteUserRole(uid,rid);

                }else{
                    userRoleTreeMapper.addUserRole(UserTools.getUUID(),uid, rid);
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public int deleteUserById(String uid) {
        return userMapper.deleteByUid(uid);
    }

    @Transactional
    @Override
    public int deleteUsers(String[] uids) throws Exception{
        if (StringTools.isEmpty(uids))
            return 0;
        int flag = 0;
        for(String uid:uids){
            if(null==userMapper.selectByUid(uid)){
               throw  new Exception("用户不存在 uid="+uid);
            }
            String[] rids = roleService.findRolesByUid(uid);
            System.out.println(rids.length);
            if(rids.length>0){
                System.out.println("用户未解绑角色 uid="+uid);
                throw  new Exception("用户未解绑角色 uid="+uid);
            }
            flag += userMapper.deleteByUid(uid);
        }
        if(flag!=uids.length){
            throw  new Exception("用户删除失败");
        }
        return flag;
    }

    @Override
    public int saveUser(User user) throws Exception{
        String uid = user.getUid();
        String username = user.getUsername();
        User  temp = userMapper.selectByUid(uid);
        if (temp!=null){
            throw new UserException.UidExisted("UID已存在！uid="+uid);
        }
        temp = userMapper.selectByUsername(username);
        if (temp!=null){
            throw new UserException.UserNameExisted("UserName已存在！username="+username);
        }
        return userMapper.insertSelective(user);
    }

    @Override
    public User findUserByUid(String uid) {
        if(StringTools.isEmpty(uid)){
            return null;
        }
       User  user = userMapper.selectByUid(uid);
        return user;
    }

    @Override
    public boolean isExist(String uid) {
        if(StringTools.isEmpty(uid)){
            return false;
        }
        User  temp = userMapper.selectByUid(uid);
        if (temp!=null){
            return true;
        }
        return false;
    }
}
