package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.model.ContactModelForAdd;
import com.netcrackerpractice.startup_social_network.model.ContactModelForDelete;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.impl.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/contacts")
public class ContactController {
    @Autowired
    ContactServiceImpl contactService;

    @Autowired
    AccountService accountService;

    @GetMapping("/{userId}")
    public List<Account> getUserContacts(@PathVariable(name = "userId") UUID userId,
                                         @RequestParam(name = "name", required = false) String name) {
        return contactService.getUserContactsAccounts(userId, name);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> addUserContact(@RequestBody ContactModelForAdd contactModelForAdd) {
        contactService.addUserInContacts(contactModelForAdd);
        return new ResponseEntity<>("User added in contact", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUserContact(@RequestBody ContactModelForDelete contactModelForDelete) {
        contactService.deleteUserFromContacts(contactModelForDelete);
        return new ResponseEntity<>("User deleted in contact", HttpStatus.OK);
    }
}
