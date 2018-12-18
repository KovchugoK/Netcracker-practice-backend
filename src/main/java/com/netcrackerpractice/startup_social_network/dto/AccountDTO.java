package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;

import java.io.Serializable;
import java.util.Set;

public class AccountDTO implements Serializable {

    private Account account;

    private BusinessRole businessRole;

    public AccountDTO(Account account, BusinessRole businessRole) {
        this.account = account;
        this.businessRole = businessRole;
    }




}
