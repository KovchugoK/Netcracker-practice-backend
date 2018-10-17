package com.netcrackerpractice.startup_social_network.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Business_roles")
public class Business_role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "businessRole")
    private List<Resume> resumes;

    @OneToMany(mappedBy = "businessRole")
    private List<Skill> skills;



    public Business_role() {
    }

    public Business_role(String roleName, List<Resume> resumes) {
        this.roleName = roleName;
        this.resumes = resumes;
    }

    public long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }
}
