package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Startups_resumes")
public class StartupResume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resume")
    @JsonIgnoreProperties(value = "startupResumes", allowSetters = true)
    private Resume resume;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_startup")
    @JsonIgnoreProperties(value = "startupResumes", allowSetters = true)
    private Startup startup;
}
