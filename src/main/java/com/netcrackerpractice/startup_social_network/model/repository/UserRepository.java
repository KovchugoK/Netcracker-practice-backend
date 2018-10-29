package com.netcrackerpractice.startup_social_network.model.repository;

import com.netcrackerpractice.startup_social_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
