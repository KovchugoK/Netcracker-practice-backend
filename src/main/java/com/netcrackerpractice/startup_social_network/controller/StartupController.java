package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.repository.StartupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StartupController {

    @Autowired
    private StartupRepository startupRepository;


    @GetMapping("/all")
    public     List<Startup> getAllStartup() {
        return startupRepository.findAll();
    }
}
