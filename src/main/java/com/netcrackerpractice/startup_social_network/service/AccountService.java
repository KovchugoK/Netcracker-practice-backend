package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Account;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    List<Account> findAll();
    Optional<Account> findAccountById(UUID uuid);
    void deleteAccountById(UUID id);
    Account saveAccount(Account startup);
    Account updateAccount(UUID id, Account startup);
    void saveImages(MultipartFile image, Account account) throws IOException, GeneralSecurityException;

}
