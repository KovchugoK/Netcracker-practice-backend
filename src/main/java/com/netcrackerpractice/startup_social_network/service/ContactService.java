package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Contact;
import com.netcrackerpractice.startup_social_network.model.ContactModelForAdd;
import com.netcrackerpractice.startup_social_network.model.ContactModelForDelete;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    List<Contact> findAll();

    List<Account> getUserContactsAccounts(UUID userId, String name);

    void addUserInContacts(ContactModelForAdd contactModelForAdd);

    void deleteUserFromContacts(ContactModelForDelete contactModelForDelete);
}
