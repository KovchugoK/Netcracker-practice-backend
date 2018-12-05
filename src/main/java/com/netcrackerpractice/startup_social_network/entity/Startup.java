package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Startups")
public class Startup {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

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
    @JoinColumn(name = "id_creator")
    @JsonIgnoreProperties(value = "startups", allowSetters = true)
    private Account account;

    public String getStartupName() {
        return startupName;
    }

    public void setStartupName(String startupName) {
        this.startupName = startupName;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getAboutProject() {
        return aboutProject;
    }

    public void setAboutProject(String aboutProject) {
        this.aboutProject = aboutProject;
    }

    public String getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(String businessPlan) {
        this.businessPlan = businessPlan;
    }

    public int getSumOfInvestment() {
        return sumOfInvestment;
    }

    public void setSumOfInvestment(int sumOfInvestment) {
        this.sumOfInvestment = sumOfInvestment;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<StartupResume> getStartupResumes() {
        return startupResumes;
    }

    public void setStartupResumes(Set<StartupResume> startupResumes) {
        this.startupResumes = startupResumes;
    }

    public Set<StartupRole> getStartupRoles() {
        return startupRoles;
    }

    public void setStartupRoles(Set<StartupRole> startupRoles) {
        this.startupRoles = startupRoles;
    }

    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "startup", allowSetters = true)
    private Set<StartupResume> startupResumes;

    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "startup", allowSetters = true)
    private Set<StartupRole> startupRoles;

    @Override
    public String toString() {
        return "Startup{" +
                "id=" + id +
                ", startupName='" + startupName + '\'' +
                ", idea='" + idea + '\'' +
                ", aboutProject='" + aboutProject + '\'' +
                ", businessPlan='" + businessPlan + '\'' +
                ", sumOfInvestment=" + sumOfInvestment +
                ", account=" + account +
                ", startupResumes=" + startupResumes +
                ", startupRoles=" + startupRoles +
                '}';
    }
}
