package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query(value = "SELECT * FROM contacts c WHERE c.id_your_account = :userId",
            nativeQuery = true)
    List<Contact> getUserContacts(@Param("userId") long userId);
}
