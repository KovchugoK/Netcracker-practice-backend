package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Favorite;

public interface FavoriteService {
    void addAccountToFavorite(Favorite favorite);
}
