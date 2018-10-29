package com.netcrackerpractice.startup_social_network.controller;

import java.util.ArrayList;
import java.util.List;

import com.netcrackerpractice.startup_social_network.entity.AccountResumeBusinessRole;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.service.AccountResumeBusinessRoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DeveloperController {


    private AccountResumeBusinessRoleService resumeBusinessRoleService;

    @GetMapping("/dev-list")
    public List<User> getAllInvestors() {
        List<AccountResumeBusinessRole> resumeBusinessRoles = new ArrayList<>();
        resumeBusinessRoles = resumeBusinessRoleService.searchUsersByRole(BusinessRoleEnum.DEVELOPER);
        List<User> accounts = new ArrayList<>();
        for (AccountResumeBusinessRole resume : resumeBusinessRoles) {
            accounts.add(resume.getAccount().getUser());
        }
        return accounts;
    }


}
