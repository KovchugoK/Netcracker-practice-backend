package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.model.repository.ResumeRepository;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public List<Resume> searchUsersByRole(BusinessRoleEnum roleEnum) {
        return resumeRepository.findAll().stream()
                .filter((s) -> s.getBusinessRole().getBusinessRoleName().name().toLowerCase().equals(roleEnum.name().toLowerCase()))
                .collect(Collectors.toList());
    }
}
