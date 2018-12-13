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
    public void addAccountToFavorite(Favorite favorite) {
        if (!isAdded(favorite.getFavoriteAccount())) {
            favoriteRepository.save(favorite);
        }
    }

    private boolean isAdded(Account account) {
        Favorite favorite = favoriteRepository.findByFavoriteAccount(account);
        return favorite != null;
    }
}
