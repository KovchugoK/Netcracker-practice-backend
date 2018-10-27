package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Startups")
public class Startup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "startup_name")
    private String startupName;

    private String idea;

    @Column(name = "about_project")
    private String aboutProject;

    @Column(name = "business_plan")
    private String businessPlan;

    @Column(name = "sum_of_investment")
    private int sumOfInvestment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creater")
    @JsonIgnoreProperties(value = "startups", allowSetters = true)
    private Account account;


    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "sturtup", allowSetters = true)
    private Set<StartupResume> startupResumes;


    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "sturtup", allowSetters = true)
    private Set<StartupRole> startupRoles;


}
