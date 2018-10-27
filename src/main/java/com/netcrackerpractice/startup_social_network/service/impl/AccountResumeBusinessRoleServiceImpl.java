package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.AccountResumeBusinessRole;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
import com.netcrackerpractice.startup_social_network.model.repository.AccountResumeBusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.service.AccountResumeBusinessRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class AccountResumeBusinessRoleServiceImpl implements AccountResumeBusinessRoleService {

    @Autowired
    private AccountResumeBusinessRoleRepository roleRepository;

    @Override
    public List<AccountResumeBusinessRole> searchUsersByRole(BusinessRoleEnum roleEnum) {
        return roleRepository.findAll().stream()
                .filter((s) -> s.getBusinessRole().equals(roleEnum.name().toLowerCase()))
                .collect(Collectors.toList());
    }
}
