package com.netcrackerpractice.startup_social_network.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String info;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<AccountResumeBusinessRole> accountResumeBusinessRoles;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<Education> educations;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ResumeSkill> resumeSkills;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<StartupResume> startupResumes;
}
