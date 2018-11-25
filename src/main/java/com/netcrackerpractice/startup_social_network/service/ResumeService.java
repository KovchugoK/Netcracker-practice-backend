package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.ResumeSkill;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ResumeService {
    List<Account> searchAccountsByRole(BusinessRoleEnum roleEnum);
    List<Resume> listAllResumes();
    Resume getResumeById(final UUID id);
    List<Account> serchAllSpecialist();
    List<BusinessRole> listBusinessRolesOfSpecialist();
    List<BusinessRole> listBusinessRolesafterFiltering(BusinessRoleEnum roleEnum);
    List<Set<ResumeSkill>> listResumeSkillsAfterFiltering(BusinessRoleEnum businessRoleEnum);
    List<Set<ResumeSkill>> listResumeSkills();
}
