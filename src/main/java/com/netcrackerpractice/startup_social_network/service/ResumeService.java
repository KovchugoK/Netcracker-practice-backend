package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;


import java.util.List;

public interface ResumeService {
    List<Resume> searchUsersByRole(BusinessRoleEnum roleEnum);
}
