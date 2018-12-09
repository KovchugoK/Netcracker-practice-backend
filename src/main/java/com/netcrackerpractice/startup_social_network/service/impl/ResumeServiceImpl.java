package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.DTO.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.*;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.repository.*;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ResumeSkillRepository resumeSkillRepository;

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
    public List<Set<ResumeSkill>> listResumeSkillsOfspecialists() {
        List<Resume> resumeList = resumeRepository.findSpecialistsResumes();
        return resumeList.stream().map(Resume::getResumeSkills).collect(Collectors.toList());
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
        if (!resume.getBusinessRole().getBusinessRoleName().equals(BusinessRoleEnum.INVESTOR)) {
            for (ResumeSkill resumeSkill : resume.getResumeSkills()) {
                resumeSkill.setResume(resume);
            }
        }
        System.out.println(resume);
        return resumeRepository.save(resume);
    }

    @Override
    public void deleteResumeSkill(UUID id, Skill skill) {
        List<ResumeSkill> resumeSkills = resumeSkillRepository.findResumeSkillByResumeId(id);
        for (ResumeSkill resume : resumeSkills) {
            if (resume.getSkill().getId().equals(skill.getId())) {
                resumeSkillRepository.delete(resume);
            }
        }
    }

    @Override
    public Resume updateResume(UUID id, Resume resume) {
        Optional<Resume> resumeData = getResumeById(id);
        if (resumeData.isPresent()) {
            Resume _resume = resumeData.get();
            _resume.setBusinessRole(resume.getBusinessRole());
            _resume.setInfo(resume.getInfo());
            Set<String> skills = new HashSet<>();
            for (ResumeSkill resumeSkill : _resume.getResumeSkills()) {
                skills.add(resumeSkill.getSkill().getSkillName());
            }
            for (ResumeSkill resumeSkill : resume.getResumeSkills()) {
                if (!skills.contains(resumeSkill.getSkill().getSkillName())) {
                    resumeSkill.setResume(resume);
                    _resume.getResumeSkills().add(resumeSkill);
                }
            }
            return saveResume(_resume);
        }
        return null;
    }

}
