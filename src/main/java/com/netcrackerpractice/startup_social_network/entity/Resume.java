package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<AccountResumeBusinessRole> accountResumeBusinessRoles;



    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<ResumeSkill> resumeSkills;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<StartupResume> startupResumes;
}
