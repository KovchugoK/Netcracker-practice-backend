package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    List<Account> getUserContactsAccounts(long userId, String name);
}
