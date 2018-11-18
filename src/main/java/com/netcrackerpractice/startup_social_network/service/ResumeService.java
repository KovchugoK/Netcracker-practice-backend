package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public interface ResumeService {
    List<Account> searchAccountsByRole(BusinessRoleEnum roleEnum);

    List<Resume> listAllResumes();

    Resume getResumeById(final UUID id);
}
