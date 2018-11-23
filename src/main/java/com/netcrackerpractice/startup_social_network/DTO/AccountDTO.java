package com.netcrackerpractice.startup_social_network.DTO;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;

import java.io.Serializable;

public class AccountDTO implements Serializable{

    private Account account;

    private BusinessRole businessRole;

    public AccountDTO(Account account, BusinessRole businessRole) {
        this.account = account;
        this.businessRole = businessRole;
    }

    public AccountDTO(){

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
}
