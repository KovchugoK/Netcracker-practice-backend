package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.DTO.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.ResumeSkill;
import com.netcrackerpractice.startup_social_network.entity.SearchObject;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AccountService {
    Account saveAccount(Account account);
    Optional<Account> findAccountById(UUID uuid);
    List<Account> findAllAccounts();
    List<AccountDTO> spesialistsAfterSearching(SearchObject searchObject);
    List<AccountDTO> buildAccountDTO(List<Account> accountList, List<BusinessRole> businessRoleList, List<Set<ResumeSkill>> setList);
}
