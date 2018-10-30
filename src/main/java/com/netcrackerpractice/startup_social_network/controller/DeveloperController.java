package com.netcrackerpractice.startup_social_network.controller;

import java.util.ArrayList;
import java.util.List;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.AccountResumeBusinessRole;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.service.AccountResumeBusinessRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DeveloperController {

    @Autowired
    private AccountResumeBusinessRoleService resumeBusinessRoleService;

    @GetMapping("/specialist-list")
    public List<Account> getAllInvestors() {
        List<AccountResumeBusinessRole> resumeBusinessRoles = new ArrayList<>();
        resumeBusinessRoles = resumeBusinessRoleService.searchUsersByRole(BusinessRoleEnum.DEVELOPER);
        List<Account> accounts = new ArrayList<>();
        for (AccountResumeBusinessRole resume : resumeBusinessRoles) {
            accounts.add(resume.getAccount());
        }
        return accounts;
    }
}
