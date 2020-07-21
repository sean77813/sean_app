package com.sean.controller;

import com.sean.model.User;
import com.sean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping("/hr/info")
    public User getCurrentHr(Authentication authentication) {
        User user = ((User) authentication.getPrincipal());
        System.out.println(user.toString());
        return user;
    }
}
