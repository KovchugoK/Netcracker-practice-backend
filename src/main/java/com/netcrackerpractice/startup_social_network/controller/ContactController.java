package com.netcrackerpractice.startup_social_network.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.DTO.ContactDTO;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ContactService;
import com.netcrackerpractice.startup_social_network.view.View;
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
    ContactService contactService;

    @Autowired
    AccountService accountService;

    @JsonView(View.BasicInfo.class)
    @GetMapping("/{userId}")
    public List<Account> getUserContacts(@PathVariable(name = "userId") UUID userId) {
        return contactService.getUserContactsAccounts(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserContact(@RequestBody ContactDTO contactDTO) {
        contactService.addUserInContacts(contactDTO);
        return new ResponseEntity<>("User added in contact", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserContact(@RequestBody ContactDTO contactDTO) {
        contactService.deleteUserFromContacts(contactDTO);
        return new ResponseEntity<>("{ deletedUser: " + contactDTO.getOtherId() + "}", HttpStatus.OK);
    }

    @JsonView(View.BasicInfo.class)
    @GetMapping("/{userId}/search")
    public List<Account> searchInUserContacts(@PathVariable(name = "userId") UUID userId,
                                              @RequestParam(name = "name", required = false) String name) {
        return contactService.searchInUserContacts(userId, name);
    }
}
