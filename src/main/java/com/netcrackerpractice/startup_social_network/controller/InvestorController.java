
package com.netcrackerpractice.startup_social_network.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.netcrackerpractice.startup_social_network.entity.AccountResumeBusinessRole;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.model.repository.UserRepository;
import com.netcrackerpractice.startup_social_network.service.AccountResumeBusinessRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class InvestorController {

    private AccountResumeBusinessRoleService resumeBusinessRoleService;

    @GetMapping("/investor-list")
    public List<User> getAllInvestors() {
        List<AccountResumeBusinessRole> resumeBusinessRoles = new ArrayList<>();
        resumeBusinessRoles = resumeBusinessRoleService.searchUsersByRole(BusinessRoleEnum.IVESTOR);
        List<User> accounts = new ArrayList<>();
        for (AccountResumeBusinessRole resume : resumeBusinessRoles) {
            accounts.add(resume.getAccount().getUser());
        }
        return accounts;
    }


}