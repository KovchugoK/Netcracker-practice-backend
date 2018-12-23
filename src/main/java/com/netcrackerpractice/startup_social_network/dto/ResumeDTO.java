package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResumeDTO {

    private UUID id;
    private String info;
    private BusinessRoleDTO businessRole;
    private Set<Skill> resumeSkills;
    private Account account;
    // private Set<ResumeSkillDTO> resumeSkills;
    //private Set<StartupResumeDTO> startupResumes;
    // private DetailAccountDTO account;
}
