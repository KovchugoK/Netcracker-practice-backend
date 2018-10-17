package com.netcrackerpractice.startup_social_network.entity;

import javax.persistence.*;

@Entity
@Table(name = "Resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String info;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "id_business_role")
    private Business_role businessRole;

    public Resume() {
    }

    public Resume(String info, Account account, Business_role businessRole) {
        this.info = info;
        this.account = account;
        this.businessRole = businessRole;
    }

    public long getId() {
        return id;
    }

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

    public Business_role getBusinessRole() {
        return businessRole;
    }
    public void setBusinessRole(Business_role businessRole) {
        this.businessRole = businessRole;
    }
}
