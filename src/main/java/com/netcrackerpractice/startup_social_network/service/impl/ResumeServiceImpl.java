package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.repository.ResumeRepository;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public List<Account> searchAccountsByRole(BusinessRoleEnum businessRoleEnum) {
       List<Resume> resumes = resumeRepository.findAll().stream()
                .filter((s) -> s.getBusinessRole().getBusinessRoleName().name().toLowerCase()
                        .equals(businessRoleEnum.name().toLowerCase()))
       return resumes.stream().map(Resume::getAccount).collect(Collectors.toList());
    }

    @Override
    public List<Resume> listAllResumes() {
        return resumeRepository.findAll();
    }

    @Override
    public Resume getResumeById(final UUID id) {
        Optional<Resume> optionalResume = resumeRepository.findById(id);
        return optionalResume.orElse(null);
    }
}
