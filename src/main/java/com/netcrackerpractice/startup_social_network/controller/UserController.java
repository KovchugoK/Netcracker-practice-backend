package com.netcrackerpractice.startup_social_network.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.netcrackerpractice.startup_social_network.model.entity.User;
import com.netcrackerpractice.startup_social_network.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/user-list")
    public List<User> getCustomersTest() {
        System.out.println("Get all accounts...");
        List<User> accounts = new ArrayList<>();
        repository.findAll().forEach(accounts::add);
        return accounts;
    }


}