package com.netcrackerpractice.startup_social_network.DTO;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.ResumeSkill;

import java.io.Serializable;
import java.util.Set;

public class AccountDTO implements Serializable {

    private Account account;

    private BusinessRole businessRole;

    private Set<ResumeSkill> resumeSkillSet;

    public AccountDTO(Account account, BusinessRole businessRole, Set<ResumeSkill> resumeSkillSet) {
        this.account = account;
        this.businessRole = businessRole;
        this.resumeSkillSet = resumeSkillSet;
    }

    public AccountDTO() {

    }

    public AccountDTO(Account account, BusinessRole businessRole) {

    }

    public Set<ResumeSkill> getResumeSkillSet() {
        return resumeSkillSet;
    }

    public void setResumeSkillSet(Set<ResumeSkill> resumeSkillSet) {
        this.resumeSkillSet = resumeSkillSet;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountDTO(Account account) {
        this.account = account;
    }

    public BusinessRole getBusinessRole() {
        return businessRole;
    }

    public void setBusinessRole(BusinessRole businessRole) {
        this.businessRole = businessRole;
    }
}
