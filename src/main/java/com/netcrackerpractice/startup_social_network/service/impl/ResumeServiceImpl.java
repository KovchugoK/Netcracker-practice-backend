package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.DTO.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.*;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.repository.*;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;

    @Autowired
    private ResumeSkillRepository resumeSkillRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public List<Account> searchAccountsByRole(BusinessRoleEnum businessRoleEnum) {
        BusinessRole businessRoleName = businessRoleRepository.findBusinessRoleByBusinessRoleName(businessRoleEnum);
        List<Resume> resumeList = resumeRepository.findResumeByBusinessRole(businessRoleName);
        return accounts(resumeList);
    }

   /* @Override
    public List<BusinessRole> listBusinessRolesafterFiltering(BusinessRoleEnum businessRoleEnum) {
        BusinessRole businessRole = businessRoleRepository.findBusinessRoleByBusinessRoleName(businessRoleEnum);
        List<Resume> resumeList = resumeRepository.findResumeByBusinessRole(businessRole);
        List<BusinessRole> businessRoleList = new ArrayList<>();
        for (Resume resume : resumeList) {
            businessRoleList.add(resume.getBusinessRole());
        }
        return businessRoleList;
    }*/

   /* @Override
    public List<Set<ResumeSkill>> listResumeSkillsAfterFiltering(BusinessRoleEnum businessRoleEnum) {
        BusinessRole businessRole = businessRoleRepository.findBusinessRoleByBusinessRoleName(businessRoleEnum);
        List<Resume> resumeList = resumeRepository.findResumeByBusinessRole(businessRole);
        List<Set<ResumeSkill>> list = new ArrayList<>();
        for (Resume resume : resumeList) {
            list.add(resumeSkillRepository.findResumeSkillByResume(resume));
        }
        return list;
    }*/



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
        List<Resume> resumeList = resumeRepository.findSpecialistsResumes();
        return resumeList.stream().map(Resume::getBusinessRole).collect(Collectors.toList());
    }

    @Override
    public List<Set<ResumeSkill>> listResumeSkillsOfspecialists() {
        List<Resume> resumeList = resumeRepository.findSpecialistsResumes();
        return resumeList.stream().map(Resume::getResumeSkills).collect(Collectors.toList());
    }

    @Override
    public List<Account> serchAllSpecialist() {
        List<Resume> resumeList = resumeRepository.findSpecialistsResumes();
        return accounts(resumeList);
    }


    private List<Account> accounts(List<Resume> resumeList) {
        return resumeList.stream().map(Resume::getAccount).collect(Collectors.toList());
    }



}
