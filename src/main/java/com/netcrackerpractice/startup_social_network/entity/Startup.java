package com.netcrackerpractice.startup_social_network.entity;

import javax.persistence.*;

@Entity
@Table(name = "Startups")
public class Startup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String startupName;
    private String idea;
    private String aboutProgect;
    private String businessPlan;
    private int sumOfInvestment;

    public Startup() {
    }

    public Startup(String startupName, String idea, String aboutProgect, String businessPlan, int sumOfInvestment) {
        this.startupName = startupName;
        this.idea = idea;
        this.aboutProgect = aboutProgect;
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

    public String getAboutProgect() {
        return aboutProgect;
    }

    public void setAboutProgect(String aboutProgect) {
        this.aboutProgect = aboutProgect;
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
