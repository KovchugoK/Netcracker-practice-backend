package com.netcrackerpractice.startup_social_network.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DeveloperController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private FavoriteService favoriteService;


    @GetMapping("/specialist-list")
    public List<Account> getAllSpecialist() {
        return resumeService.searchAccountsByRole(BusinessRoleEnum.DEVELOPER);
    }

    @PostMapping(value = "/specialist-list")
    public ResponseEntity<Account> addAccountToFav(@RequestBody Account account) {
        favoriteService.addAccountToFavorite(account);
        return ResponseEntity.ok(account);
    }

}
