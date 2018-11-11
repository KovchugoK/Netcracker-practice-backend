package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Contact;
import com.netcrackerpractice.startup_social_network.repository.ContactRepository;
import com.netcrackerpractice.startup_social_network.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Account> getUserContactsAccounts(long userId, String name) {
        List<Account> accountEntityList = getOthersAccounts(contactRepository.getUserContacts(userId));

        if (name != null) {
            accountEntityList = findByName(accountEntityList, name);
        }
        return accountEntityList;
    }

    private List<Account> getOthersAccounts(List<Contact> contactEntities) {
        List<Account> accountEntityList = new ArrayList<>();
        for (Contact contactEntity : contactEntities) {
            accountEntityList.add(contactEntity.getOtherAccount());
        }
        return accountEntityList;
    }

    private List<Account> findByName(List<Account> accountEntityList, String name) {
        String[] names = name.trim().split(" ");
        String firstName = names[0];
        String lastName;
        if (names.length != 1)
            lastName = names[1];
        else
            lastName = "";

        return accountEntityList.stream().filter(accountEntity ->
                accountEntity.getFirstName().contains(firstName) && accountEntity.getSecondName().contains(lastName) ||
                        accountEntity.getFirstName().contains(lastName) && accountEntity.getSecondName().contains(firstName)
        ).collect(Collectors.toList());
    }
}
