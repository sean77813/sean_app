package com.sean;

import com.sean.mapper.RoleMenuTreeMapper;
import com.sean.model.RoleMenuTree;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeanWebApplicationTests {

    @Autowired
    private RoleMenuTreeMapper roleMenuTreeMapper;

    @Test
    public void Test1() {

        List<RoleMenuTree> list = new ArrayList<>();
        list.add(new RoleMenuTree("7","6","7"));
        list.add(new RoleMenuTree("9","6","9"));
        list.add(new RoleMenuTree("10","6","10"));
        list.add(new RoleMenuTree("11","6","11"));
        list.add(new RoleMenuTree("12","6","12"));
        list.add(new RoleMenuTree("13","6","13"));
        list.add(new RoleMenuTree("14","6","14"));
        list.add(new RoleMenuTree("15","6","15"));
        list.add(new RoleMenuTree("16","6","16"));
        list.add(new RoleMenuTree("17","6","17"));
        list.add(new RoleMenuTree("18","6","18"));
        list.add(new RoleMenuTree("19","6","19"));
        list.add(new RoleMenuTree("20","6","20"));
        list.add(new RoleMenuTree("21","6","21"));
        list.add(new RoleMenuTree("22","6","22"));
        list.add(new RoleMenuTree("23","6","23"));
        list.add(new RoleMenuTree("24","6","24"));
        list.add(new RoleMenuTree("25","6","25"));
        list.add(new RoleMenuTree("26","6","26"));
        list.add(new RoleMenuTree("27","6","27"));
        list.add(new RoleMenuTree("28","6","28"));
        System.out.println(list.size());
        for(RoleMenuTree menuTree:list){
            System.out.println(menuTree.toString());
            System.out.println(roleMenuTreeMapper==null);
            roleMenuTreeMapper.insert(menuTree);
        }
    }


}
