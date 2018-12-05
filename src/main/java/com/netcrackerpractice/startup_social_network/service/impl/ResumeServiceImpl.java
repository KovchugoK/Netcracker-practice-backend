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
        boolean isSkill = false;
        if (resumeData.isPresent()) {
            Resume _resume = (Resume)resumeData.get();
            _resume.setBusinessRole(resume.getBusinessRole());
            _resume.setInfo(resume.getInfo());

           for (ResumeSkill resumeSkill: resume.getResumeSkills()) {
                if(!_resume.getResumeSkills().contains(resumeSkill)){
                    _resume.getResumeSkills().add(resumeSkill);
                }
            }
          /* for (ResumeSkill resumeSkill: resume.getResumeSkills()){
                for (ResumeSkill resumeSkill1: _resume.getResumeSkills()){
                    Skill skill = (Skill)resumeSkill1.getSkill();
                    System.out.println(resumeSkill.getSkill().getId());
                    System.out.println(resumeSkill.getSkill().getClass());
                    System.out.println();
                    System.out.println(skill.getId());
                    System.out.println(skill.getClass().getSuperclass());
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println(resumeSkill1.getSkill().equals(resumeSkill.getSkill()));
                    //System.out.println(resumeSkill.getSkill().getId().equals(resumeSkill1.getSkill().getId()));
                    System.out.println();
                }
            }*/
            for (ResumeSkill resumeskill : _resume.getResumeSkills()) {
                resumeskill.setResume(resume);
            }
            return saveResume(_resume);
        }
        return null;
    }

}
