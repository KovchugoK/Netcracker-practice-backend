package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
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
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<Education> educations;


    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<ResumeSkill> resumeSkills;


    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<StartupResume> startupResumes;


    @ManyToOne
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties(value = "resumes", allowSetters = true)
    private Account account;

    @Enumerated(EnumType.STRING)
    private BusinessRoleEnum businessRole;
}
