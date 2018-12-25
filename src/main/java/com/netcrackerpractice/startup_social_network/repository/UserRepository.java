package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginOrEmail(String login, String email);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
    Optional<User> findById(UUID userIds);
    Optional<User> findByLogin(String login);

}
