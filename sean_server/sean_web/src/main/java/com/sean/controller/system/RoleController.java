package com.sean.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.sean.model.RespBean;
import com.sean.model.Role;
import com.sean.service.MenuService;
import com.sean.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/listrole")
    public List<Role> getRoleata(){
        return roleService.getAllRoles();
    }

    @PostMapping("/save")
    public RespBean saveUser(@RequestBody Role role){
        System.out.println(role.toString());
        if(roleService.isExist(role.getRid())){
            return RespBean.error("角色已存在!");
        }
        try {
            if (roleService.saveRole(role)== 1) {
                return RespBean.ok("保存成功!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return RespBean.error("保存失败!");
    }

    @PostMapping("/edit")
    public RespBean editUser(@RequestBody Role role){
        System.out.println(role.toString());
        if(roleService.isExist(role.getRid())){
            if (roleService.editRoleName(role)==1){
                return RespBean.ok("保存成功!");
            }
            return RespBean.ok("保存失败!");
        }else{
            return RespBean.error("保存失败!");
        }
    }

    @PostMapping("/getrolesbyuid")
    public String[] getRolesByUid(@RequestParam("uid") String uid){
       return  roleService.findRolesByUid(uid);
    }
}
