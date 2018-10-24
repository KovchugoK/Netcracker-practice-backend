package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<AccountResume> accountResumes;

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<Education> educations;

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<ResumeSkill> resumeSkills;

    @JsonManagedReference
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private Set<StartupResume> startupResumes;
}
