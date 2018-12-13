package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.DTO.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.*;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.repository.*;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    private static final int ZERO_LEN = 0;
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;



    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Resume> spesialistsAfterSearching(SearchObject searchObject) {
        if (searchObject.getSkills().length == ZERO_LEN && searchObject.getRoles().length == ZERO_LEN && searchObject.getSearchString() != null) {
            return resumeRepository.findResumeByName(searchObject.getSearchString());
        } else if (searchObject.getSkills().length != ZERO_LEN && searchObject.getRoles().length == ZERO_LEN && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            for (String skillName : searchObject.getSkills()) {
                setResume.addAll(resumeRepository.findResumeBySkiillName(skillName));
            }
            return new ArrayList<>(setResume);
        } else if (searchObject.getSkills().length == ZERO_LEN && searchObject.getRoles().length != ZERO_LEN && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                List<Resume> resumeList = resumeRepository.findResumeByBusinessRoleName(roleName.toUpperCase());
                setResume.addAll(resumeList);
            }
            return new ArrayList<>(setResume);
        } else if (searchObject.getSkills().length != ZERO_LEN && searchObject.getRoles().length == ZERO_LEN && searchObject.getSearchString() != null) {
            Set<Resume> setResume = new HashSet<>();
            for (String skillName : searchObject.getSkills()) {
                setResume.addAll(resumeRepository.findResumeBySkillNameAndAccountName(skillName, searchObject.getSearchString()));
            }
            return new ArrayList<>(setResume);
        } else if (searchObject.getSkills().length == ZERO_LEN && searchObject.getRoles().length != ZERO_LEN && searchObject.getSearchString() != null) {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                setResume.addAll(resumeRepository.findResumeByRoleNameAndAccountName(roleName.toUpperCase(), searchObject.getSearchString()));
            }
            return new ArrayList<>(setResume);
        } else if (searchObject.getSkills().length != ZERO_LEN && searchObject.getRoles().length != ZERO_LEN && searchObject.getSearchString() == null) {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                for (String skillName : searchObject.getSkills()) {
                    setResume.addAll(resumeRepository.findResumeByRoleNameAndSkillName(roleName.toUpperCase(), skillName));
                }
            }
            new ArrayList<>(setResume);
        } else {
            Set<Resume> setResume = new HashSet<>();
            for (String roleName : searchObject.getRoles()) {
                for (String skillName : searchObject.getSkills()) {
                    setResume.addAll(resumeRepository.findResumeByRoleNameAndSkillNameAndName(roleName.toUpperCase(), skillName, searchObject.getSearchString()));
                }
            }
        }
        return null;
    }

    @Override
    public List<Resume> searchAccountsByRole(BusinessRoleEnum businessRoleEnum) {
        BusinessRole businessRoleName = businessRoleRepository.findBusinessRoleByBusinessRoleName(businessRoleEnum);
        return resumeRepository.findResumeByBusinessRole(businessRoleName);
    }


    @Override
    public Optional<Resume> getResumeById(final UUID id) {
        return resumeRepository.findById(id);
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
    public List<Set<Skill>> listResumeSkillsOfspecialists() {
       /* List<Resume> resumeList = resumeRepository.findSpecialistsResumes();
        return resumeList.stream().map(Resume::getResumeSkills).collect(Collectors.toList());*/
    return null;
    }

    @Override
    public List<Resume> serchAllSpecialist() {
        return resumeRepository.findSpecialistsResumes();
    }


    private List<Account> accounts(List<Resume> resumeList) {
        return resumeList.stream().map(Resume::getAccount).collect(Collectors.toList());
    }


    @Override
    public void deleteResumeById(UUID id) {
        resumeRepository.deleteById(id);
    }

    @Override
    public Resume saveResume(Resume resume) {

        for (Skill skill: resume.getResumeSkills()) {
            resume.addSkill(skill);
        }
        return resumeRepository.save(resume);
    }

    @Override
    public Resume updateResume(UUID id, Resume resume) {
        Optional<Resume> resumeData = getResumeById(id);
        if (resumeData.isPresent()) {
            Resume _resume = resumeData.get();
            _resume.setBusinessRole(resume.getBusinessRole());
            _resume.setInfo(resume.getInfo());
            _resume.setResumeSkills(resume.getResumeSkills());
            System.out.println(_resume);
            return resumeRepository.save(_resume);
        }
        return null;
    }
}
