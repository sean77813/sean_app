package com.sean.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.sean.model.Menu;
import com.sean.model.MenuTemplate;
import com.sean.model.RespBean;
import com.sean.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class MenuController {
    //listmenu
    @Autowired
    private MenuService menuService;

    @GetMapping("/listmenu")
    public JSONArray getMenuData(){
        JSONArray result = menuService.getMenuTree(null);
        return result;
    }

    @GetMapping("/getmenubyrid")
    public String[] getMenuByRole(@RequestParam("rid") String rid){
        System.out.println("rid=>"+rid);
        return menuService.findMenusByRole(rid);
    }

    @GetMapping("/saverolemenus")
    public RespBean saveRoleMenus(@RequestParam("menus") String[] menus, @RequestParam("rid") String rid){
        if(menus!=null){
            for(String i:menus){
                System.out.println(i);
            }
        }
        System.out.println("rid=>"+rid);
        if(menuService.saveRoleMenus(menus, rid)>0){
            return RespBean.ok("保存成功!");
        }else{
            return RespBean.error("保存失败！");
        }
    }

    @PostMapping("/add")
    public RespBean addMenu(@RequestBody MenuTemplate menu){
        System.out.println(menu.toString());
        try {
            if (menuService.addMenu(menu)==1){
                return RespBean.ok("保存成功!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return RespBean.error(e.getMessage());
        }
        return RespBean.error("保存失败！");
    }

    @PostMapping("/edit")
    public RespBean editMenu(@RequestBody Menu menu){
        System.out.println(menu.toString());
        try {
            if (menuService.editMenu(menu)==1){
                return RespBean.ok("保存成功!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return RespBean.error(e.getMessage());
        }
       return RespBean.error("保存失败！");
    }


}
