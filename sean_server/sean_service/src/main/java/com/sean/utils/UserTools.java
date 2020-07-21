package com.sean.utils;

import com.sean.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class UserTools {

    /**
     * 获取当前登录用户
     * @return
     */
    public static User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public static String getUUID(){
       return UUID.randomUUID().toString().replace("-", "");
    }
}
