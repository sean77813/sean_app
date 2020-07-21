package com.sean.controller.system;

import com.sean.model.RespBean;
import com.sean.model.Role;
import com.sean.model.User;
import com.sean.service.RoleService;
import com.sean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class UserController {
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
            return RespBean.error("用户名或uid已存在!");
        }
        return RespBean.error("更新失败!");
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(String uid, String[] rids) {
        if (userService.updateUserRole(uid, rids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHrById(@PathVariable String uid) {
        if (userService.deleteUserById(uid) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
