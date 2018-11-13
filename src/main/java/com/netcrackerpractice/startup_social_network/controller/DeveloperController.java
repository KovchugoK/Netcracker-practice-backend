package com.netcrackerpractice.startup_social_network.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class DeveloperController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/specialist-list")
    public List<Account> getAllDevelopers() {
        List<Resume> resumeBusinessRoles = resumeService.searchUsersByRole(BusinessRoleEnum.DEVELOPER);
        return resumeBusinessRoles.stream().map(Resume::getAccount).collect(Collectors.toList());

    }
}
