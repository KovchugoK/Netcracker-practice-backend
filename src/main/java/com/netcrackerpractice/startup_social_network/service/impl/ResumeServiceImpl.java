package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.repository.ResumeRepository;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;

    @Override
    public List<Account> searchAccountsByRole(BusinessRoleEnum businessRoleEnum) {
        BusinessRole businessRoleName = businessRoleRepository.findBusinessRoleByBusinessRoleName(businessRoleEnum);
        List<Resume> resumeList = resumeRepository.findResumeByBusinessRole(businessRoleName);
        return accounts(resumeList);
    }

    @Override
    public List<BusinessRole> listBusinessRolesafterFiltering(BusinessRoleEnum businessRoleEnum) {
        BusinessRole businessRole = businessRoleRepository.findBusinessRoleByBusinessRoleName(businessRoleEnum);
        List<Resume> resumeList = resumeRepository.findResumeByBusinessRole(businessRole);
        List<BusinessRole> businessRoleList = new ArrayList<>();
        for (Resume resume : resumeList) {
            businessRoleList.add(resume.getBusinessRole());
        }
        return businessRoleList;
    }


    @Override
    public Resume getResumeById(final UUID id) {
        Optional<Resume> optionalResume = resumeRepository.findById(id);
        return optionalResume.orElse(null);
    }

    @Override
    public List<Resume> listAllResumes() {
        return resumeRepository.findAll();
    }

    @Override
    public List<BusinessRole> listBusinessRolesOfSpecialist() {
        List<Resume> resumeList = getSpecialists();
        return resumeList.stream().map(Resume::getBusinessRole).collect(Collectors.toList());
    }

    @Override
    public List<Account> serchAllSpecialist() {
        List<Resume> resumeList = getSpecialists();
        return accounts(resumeList);
    }

    private List<Resume> getSpecialists() {
        return resumeRepository.findAll().stream()
                .filter((s) -> !s.getBusinessRole().getBusinessRoleName().name().toLowerCase()
                        .equals(BusinessRoleEnum.INVESTOR.toString().toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Account> accounts(List<Resume> resumeList) {
        return resumeList.stream().map(Resume::getAccount).collect(Collectors.toList());
    }


}
