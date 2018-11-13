package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contacts")
public class ContactController {
    @Autowired
    ContactServiceImpl contactService;

    @Autowired
    AccountService accountService;

    @GetMapping("/{userId}")
    public List<Account> getUserContacts(@PathVariable(name = "userId") long userId,
                                         @RequestParam(name = "name", required = false) String name) {
        return contactService.getUserContactsAccounts(userId, name);
    }

}
