package com.netcrackerpractice.startup_social_network.entity;

import javax.persistence.*;

@Entity
@Table(name = "Startups")
public class Startup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Startup() {
    }

    public Startup(String startupName, String idea, String aboutProject, String businessPlan, int sumOfInvestment) {
        this.startupName = startupName;
        this.idea = idea;
        this.aboutProject = aboutProject;
        this.businessPlan = businessPlan;
        this.sumOfInvestment = sumOfInvestment;
    }

    public long getId() {
        return id;
    }

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
    public void setAboutProgect(String aboutProject) {
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
}
