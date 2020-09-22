package com.swee.model.core.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swee.model.core.member.model.User;
import com.swee.model.core.member.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void create(@RequestBody User user){
        user = User.builder().mobile("13423452345")
                .name("tom").email("123@qq.com")
                .enableSign(true)
                .build();
        userService.createEX(user);
    }
}
