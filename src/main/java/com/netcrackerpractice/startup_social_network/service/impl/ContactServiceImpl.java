package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Contact;
import com.netcrackerpractice.startup_social_network.model.ContactModel;
import com.netcrackerpractice.startup_social_network.repository.ContactRepository;
import com.netcrackerpractice.startup_social_network.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    public List<Account> searchInUserContacts(UUID userId, String name) {
        List<Account> accountEntityList = getOthersAccounts(contactRepository.getUserContacts(userId));

        if (name != null) {
            accountEntityList = findByName(accountEntityList, name);
        }
        return accountEntityList;
    }

    @Override
    public List<Account> getUserContactsAccounts(UUID userId) {
        return getOthersAccounts(contactRepository.getUserContacts(userId));
    }

    @Override
    public void addUserInContacts(ContactModel contactModel) {
        contactRepository.addUserInContacts(contactModel.getYourId(), contactModel.getOtherId());
    }

    @Override
    public void deleteUserFromContacts(ContactModel contactModel) {
        contactRepository.deleteUserFromContacts(
                contactModel.getYourId(),
                contactModel.getOtherId()
        );
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
        String firstName = names[0], lastName;

        if (names.length != 1)
            lastName = names[1];
        else
            lastName = "";

        return accountEntityList.stream()
                .filter(accountEntity ->
                        accountEntity.getFirstName().contains(firstName)
                                && accountEntity.getLastName().contains(lastName)
                                ||
                                accountEntity.getFirstName().contains(lastName)
                                        && accountEntity.getLastName().contains(firstName)
                ).collect(Collectors.toList());
    }
}
