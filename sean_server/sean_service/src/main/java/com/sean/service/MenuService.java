package com.sean.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sean.mapper.MenuMapper;
import com.sean.mapper.RoleMenuTreeMapper;
import com.sean.model.*;
import com.sean.utils.StringTools;
import com.sean.utils.UserTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
@CacheConfig(cacheNames = "menus_cache")
public class MenuService implements MenuServiceInterface{
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RoleMenuTreeMapper roleMenuMapper;
    @Autowired
    UserService userService;
    public List<Menu> getMenusByUserId() {
        return menuMapper.getMenusByUserId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUid());
    }

    @Override
    public List<Menu> findMenusByPid(String pid) {
        if(StringTools.isEmpty(pid)){
            return null;
        }
        return menuMapper.selectByPid(pid);
    }

    @Override
    public String[] findMenusByRole(String rid) {
        if(StringTools.isEmpty(rid))
            return null;
        return menuMapper.findMenusByRid(rid);
    }

    @Transactional
    @Override
    public int saveRoleMenus(String[] menus, String rid) {
        if(StringTools.isEmpty(rid))
            return 0;
        try {
            String[] oldMenus = menuMapper.findMenusByRid(rid);
            String[] minus = StringTools.minus(oldMenus,menus);
            for(String mid : minus){
                if(Arrays.asList(oldMenus).contains(mid)){
                    roleMenuMapper.deleteRoleMenu(rid, mid);
                }else{
                    roleMenuMapper.addRoleMenu(UserTools.getUUID(), rid, mid);
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
        return 1;
    }


    @Override
    public int addMenu(MenuTemplate menu) {
        menu.setId(UserTools.getUUID());
        menu.setRequireauth(true);
        menu.setKeepalive(true);
        return menuMapper.insertMenu(menu);
    }


    @Override
    public int editMenu(Menu menu) {
        return 0;
    }

    public Menu findRootMenu(){
        return menuMapper.selectByPrimaryKey("1");
    }

    public JSONArray getMenuTree(String uid){
        List<Menu> menus = new ArrayList<>();
        if (StringTools.isEmpty(uid)){
            menus = menuMapper.findMenuAll();
        }else{
            if(!userService.isExist(uid))
                return null;

            menus = menuMapper.getMenusByUserId(uid);
        }
        JSONArray result = new JSONArray();
        if(menus!=null && menus.size()>0){
            result = listToTree(JSONArray.parseArray(JSON.toJSONString(menus)),"id","pid","children");
        }
        return result;
    }


    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child) {
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for (int i = 0; i < arr.size(); i++) {
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for (int j = 0; j < arr.size(); j++) {
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            String pidStr = "";
            Object pidObj = aVal.get(pid);
            if (aVal.get(pid) != null) {
                pidStr = aVal.get(pid).toString();
            }
            JSONObject hashVP = (JSONObject) hash.get(pidStr);
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if (hashVP != null) {
                //检查是否有child属性
                if (hashVP.get(child) != null) {
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                } else {
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            } else {
                r.add(aVal);
            }
        }
        return r;
    }

//    @Cacheable
//    public List<Menu> getAllMenusWithRole() {
//        return menuMapper.getAllMenusWithRole();
//    }
//
//    public List<Menu> getAllMenus() {
//        return menuMapper.getAllMenus();
//    }
//
//    public List<Integer> getMidsByRid(Integer rid) {
//        return menuMapper.getMidsByRid(rid);
//    }
//
//    @Transactional
//    public boolean updateMenuRole(Integer rid, Integer[] mids) {
//        menuRoleMapper.deleteByRid(rid);
//        if (mids == null || mids.length == 0) {
//            return true;
//        }
//        Integer result = menuRoleMapper.insertRecord(rid, mids);
//        return result==mids.length;
//    }
}
