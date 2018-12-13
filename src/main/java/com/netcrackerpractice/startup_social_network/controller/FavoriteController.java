package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Favorite;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FavoriteController {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/favorites/{id}")
    public List<Favorite> getFavorites(@PathVariable UUID id) {
        Account account = accountRepository.findById(id).get();
        return favoriteRepository.findByAccount(account);
    }

    @DeleteMapping("/favorites/{id}")
    public void deleteFavorite(@PathVariable UUID id) {
        Account account = accountRepository.findById(id).get();
        favoriteRepository.deleteByFavoriteAccount(account);
        System.out.println(id);
        favoriteRepository.deleteById(id);
    }


}
