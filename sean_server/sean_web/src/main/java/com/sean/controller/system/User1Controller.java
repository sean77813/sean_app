package com.sean.controller.system;

import com.sean.model.RespBean;
import com.sean.model.Role;
import com.sean.model.User;
import com.sean.service.RoleService;
import com.sean.service.UserService;
import com.sean.utils.UserTools;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class User1Controller {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @GetMapping("/")
    public List<User> getAllHrs(String keywords) {
        return userService.getAllUsers(keywords);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody User user) {
        try {
            if (userService.updateUser(user) == 1) {
                return RespBean.ok("更新成功!");
            }
        }catch (Exception e){
            return RespBean.error(e.getMessage());
        }
        return RespBean.error("更新失败!");
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/role")
    public RespBean updateHrRole(String uid, String[] rids) {
        if (userService.updateUserRole(uid, rids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/delete")
    public RespBean deleteUserById(@RequestParam("uids") String[] uids) {
        try {
            userService.deleteUsers(uids);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
        return RespBean.ok("删除成功!");
    }

    @PostMapping("/save")
    public RespBean saveUser(@RequestBody User user){
        System.out.println(user.toString());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setId(UserTools.getUUID());
        user.setUid(user.getUid());
        try {
            if (userService.saveUser(user) == 1) {
                return RespBean.ok("保存成功!");
            }
        }catch (Exception e){
            return RespBean.error(e.getMessage());
        }
        return RespBean.error("保存失败!");
    }


    @PostMapping("/edit")
    public RespBean editUser(@RequestBody User user){
        System.out.println(user.toString());
        try {
            if (userService.updateUser(user) == 1) {
                return RespBean.ok("保存成功!");
            }
        }catch (Exception e){
            System.out.println(e);
            return RespBean.error("用户已存在!");
        }
        return RespBean.error("保存失败!");
    }
}
