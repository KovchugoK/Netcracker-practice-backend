package com.netcrackerpractice.startup_social_network.controllers;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.repositories.StartupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class StartupController {

    @Autowired
    private StartupRepository repository;

    @GetMapping("/startup-list")
    public List<Startup> getStartupList() {
        return repository.findAll();
    }
}