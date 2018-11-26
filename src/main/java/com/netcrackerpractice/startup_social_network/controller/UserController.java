package com.netcrackerpractice.startup_social_network.controller;


import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.User;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import com.netcrackerpractice.startup_social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Autowired
    AccountService accountService;

    @GetMapping("/user-list")
    public List<User> getStartupList() {
        return userService.findAll();
    }

    @PostMapping("/image")
    private ResponseEntity<?> saveImages(@RequestParam("image") MultipartFile image,
                                             @RequestParam(name = "Id") UUID accountId) {
        try {
            Optional<Account> accountOptional = accountService.findAccountById(accountId);

            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                accountService.saveImages(image, account);
            } else
                return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAIL to upload. Did't find account");

            return ResponseEntity.status(HttpStatus.OK).body("You successfully uploaded");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAIL to upload");
        }
    }
}
