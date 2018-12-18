package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.dto.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
<<<<<<< HEAD

import com.netcrackerpractice.startup_social_network.entity.SearchObject;
=======
import com.netcrackerpractice.startup_social_network.entity.ResumeSkill;
>>>>>>> dev

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AccountService {
    List<Account> findAll();
    Optional<Account> findAccountById(UUID uuid);

    List<AccountDTO> buildAccountDTO(List<Account> accountList, List<BusinessRole> businessRoleList);

    void deleteAccountById(UUID id);
    Account saveAccount(Account startup);

    Account updateAccount(UUID id, Account startup, String image);
}
