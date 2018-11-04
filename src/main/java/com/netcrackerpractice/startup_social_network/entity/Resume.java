package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Resumes")
public class Resume {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties(value = "resumes", allowSetters = true)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_business_role")
    @JsonIgnoreProperties(value = "resumes", allowSetters = true)
    private BusinessRole businessRole;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<ResumeSkill> resumeSkills;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "resume", allowSetters = true)
    private Set<StartupResume> startupResumes;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BusinessRole getBusinessRole() {
        return businessRole;
    }

    public void setBusinessRole(BusinessRole businessRole) {
        this.businessRole = businessRole;
    }

    public Set<ResumeSkill> getResumeSkills() {
        return resumeSkills;
    }

    public void setResumeSkills(Set<ResumeSkill> resumeSkills) {
        this.resumeSkills = resumeSkills;
    }

    public Set<StartupResume> getStartupResumes() {
        return startupResumes;
    }

    public void setStartupResumes(Set<StartupResume> startupResumes) {
        this.startupResumes = startupResumes;
    }
}
