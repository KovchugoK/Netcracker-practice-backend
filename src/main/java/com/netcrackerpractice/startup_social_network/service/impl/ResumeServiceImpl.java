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

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;

    @Autowired
    private ResumeSkillRepository resumeSkillRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<Account> searchAccountsByRole(BusinessRoleEnum businessRoleEnum) {
        BusinessRole businessRoleName = businessRoleRepository.findBusinessRoleByBusinessRoleName(businessRoleEnum);
        List<Resume> resumeList = resumeRepository.findResumeByBusinessRole(businessRoleName);
        return accounts(resumeList);
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
    public List<Account> serchAllSpecialist() {
        List<Resume> resumeList = resumeRepository.findSpecialistsResumes();
        return accounts(resumeList);
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
        if (!resume.getBusinessRole().getBusinessRoleName().equals("INVESTOR")) {
            Set<ResumeSkill> skillSet = new HashSet<>();
            for (ResumeSkill resumeSkill : resume.getResumeSkills()) {
                Skill skill = skillRepository.findSkillById(resumeSkill.getId());
                if (skill != null) {
                    resumeSkill.setId(null);
                    resumeSkill.setSkill(skill);
                    resumeSkill.setResume(resume);
                }
            }
            if (!skillSet.isEmpty()) {
                resume.setResumeSkills(skillSet);
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
        boolean isSkill = false;
        if (resumeData.isPresent()) {
            Resume _resume = resumeData.get();
            Set<ResumeSkill> skillSet = _resume.getResumeSkills();
            _resume.setBusinessRole(resume.getBusinessRole());
            _resume.setInfo(resume.getInfo());
            if (resume.getResumeSkills() != null || resume.getResumeSkills().size() != 0) {
                System.out.println(resume.getResumeSkills());
                for (ResumeSkill rS : resume.getResumeSkills()) {
                    Skill skill = skillRepository.findSkillById(rS.getId());
                    if (skill != null) {
                        for (ResumeSkill resumeSkill : skillSet) {
                            if (resumeSkill.getSkill().equals(skill)) {
                                isSkill = true;
                                break;
                            }
                        }
                        if (!isSkill) {
                            ResumeSkill resumeSkill = new ResumeSkill();
                            resumeSkill.setId(UUID.randomUUID());
                            resumeSkill.setResume(_resume);
                            resumeSkill.setSkill(skill);
                            skillSet.add(resumeSkill);
                            _resume.setResumeSkills(skillSet);
                            isSkill = false;
                        }
                    }
                }
            } else {
                _resume.setResumeSkills(resume.getResumeSkills());
            }
            return saveResume(_resume);
        }
        return null;
    }

}
