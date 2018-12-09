package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.ResumeSkill;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


import java.util.Set;
import java.util.UUID;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, UUID> {
    List<Resume> findResumeByBusinessRole(BusinessRole businessRole);

    @Query(value = "SELECT * FROM resumes LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id WHERE business_roles.role_name != 'INVESTOR' ", nativeQuery = true)
    List<Resume> findSpecialistsResumes();

    List<Resume> findResumeByAccount(Account account);

    List<Resume> findResumeByResumeSkills(ResumeSkill skil);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN accounts ON resumes.id_account = accounts.id " +
            "WHERE accounts.first_name LIKE %?1% ", nativeQuery = true)
    List<Resume> findResumeByName(String name);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN resumes_skills ON resumes.id = resumes_skills.id_resume " +
            "LEFT OUTER JOIN skills ON resumes_skills.id_skill = skills.id " +
            "WHERE skills.skill_name = ?1", nativeQuery = true)
    List<Resume> findResumeBySkiillName(String skillName);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id " +
            "WHERE business_roles.role_name = ?1 ", nativeQuery = true)
    List<Resume> findResumeByBusinessRoleName(String businessRolNeName);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN accounts ON resumes.id_account = accounts.id " +
            "LEFT OUTER JOIN resumes_skills ON resumes.id = resumes_skills.id_resume " +
            "LEFT OUTER JOIN skills ON resumes_skills.id_skill = skills.id " +
            "WHERE skills.skill_name = ?1 AND accounts.first_name LIKE %?2%", nativeQuery = true)
    List<Resume> findResumeBySkillNameAndAccountName(String skillName, String accountName);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN accounts ON resumes.id_account = accounts.id " +
            "LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id  " +
            "WHERE business_roles.role_name = ?1 AND accounts.first_name LIKE %?2%", nativeQuery = true)
    List<Resume> findResumeByRoleNameAndAccountName(String roleName, String accountName);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN resumes_skills ON resumes.id = resumes_skills.id_resume " +
            "LEFT OUTER JOIN skills ON resumes_skills.id_skill = skills.id " +
            "LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id  " +
            "WHERE business_roles.role_name = ?1 AND skills.skill_name = ?2", nativeQuery = true)
    List<Resume> findResumeByRoleNameAndSkillName(String roleName, String skillName);

    @Query(value = "SELECT * FROM resumes " +
            "LEFT OUTER JOIN resumes_skills ON resumes.id = resumes_skills.id_resume " +
            "LEFT OUTER JOIN skills ON resumes_skills.id_skill = skills.id " +
            "LEFT OUTER JOIN business_roles ON resumes.id_business_role = business_roles.id  " +
            "LEFT OUTER JOIN accounts ON resumes.id_account = accounts.id " +
            "WHERE business_roles.role_name = ?1 AND skills.skill_name = ?2 AND", nativeQuery = true)
    List<Resume> findResumeByRoleNameAndSkillNameAndName(String roleName, String skillName, String name);


}
