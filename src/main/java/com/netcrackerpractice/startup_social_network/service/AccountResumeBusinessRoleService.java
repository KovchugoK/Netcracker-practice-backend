package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.AccountResumeBusinessRole;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import org.springframework.stereotype.Service;


import java.util.List;

public interface AccountResumeBusinessRoleService {
    List<AccountResumeBusinessRole> searchUsersByRole(BusinessRoleEnum roleEnum);
}
