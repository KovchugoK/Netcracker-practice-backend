package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Favorite;
import com.netcrackerpractice.startup_social_network.repository.FavoriteRepository;
import com.netcrackerpractice.startup_social_network.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void addAccountToFavorite(Account account) {
        if (!isAdded(account)) {
            Favorite favorite = new Favorite();
            favorite.setId(UUID.randomUUID());
            favorite.setAccount(account);
            System.out.println(favorite);
            favoriteRepository.save(favorite);
        }
    }

    private boolean isAdded(Account account) {
        List<Favorite> favoriteList = favoriteRepository.findAll();
        boolean isAdded = false;
        for (Favorite favorite : favoriteList) {
            if (favorite.getAccount().getId().equals(account.getId())) {
                isAdded = true;
            }
        }
        return isAdded;
    }
}
