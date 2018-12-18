package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Favorite;
import com.netcrackerpractice.startup_social_network.repository.AccountRepository;
import com.netcrackerpractice.startup_social_network.repository.FavoriteRepository;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
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

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/favorites/{id}")
    public List<Favorite> getFavorites(@PathVariable UUID id) {
        Account account = accountRepository.findById(id).get();
        return account.getFavorites();
    }

    @DeleteMapping("/favorites/{id}")
    public void deleteFavorite(@PathVariable UUID id, @RequestParam(name = "id_account") UUID id_account) {
        favoriteService.deleteFavorite(id, id_account);
    }


}
