package com.netcrackerpractice.startup_social_network.entity;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "skill_name")
    private String skillName;

    @ManyToOne()
    @JoinColumn(name = "id_business_role")
    private Business_role businessRole;

    public Skill() {
    }

    public Skill(String skillName, Business_role businessRole) {
        this.skillName = skillName;
        this.businessRole = businessRole;
    }

    public long getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Business_role getBusinessRole() {
        return businessRole;
    }
    public void setBusinessRole(Business_role businessRole) {
        this.businessRole = businessRole;
    }
}
