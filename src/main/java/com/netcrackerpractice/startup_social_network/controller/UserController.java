package com.netcrackerpractice.startup_social_network.controller;


import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user-list")
    public List<User> getStartupList() {
        return userService.findAll();
    }


}
