package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query(value = "SELECT * FROM contacts c WHERE c.id_your_account = :userId",
            nativeQuery = true)
    List<Contact> getUserContacts(@Param("userId") UUID userId);

    @Query(value = "INSERT INTO contacts(id_your_account, id_contact_account) VALUES (:whoAddId, :whomAddId)",
            nativeQuery = true)
    void addUserInContacts(@Param("whoAddId") UUID whoAddId, @Param("whomAddId") UUID whomAddId);

    @Query(value = "DELETE FROM contacts c WHERE c.id_your_account = :whoDeleteId AND c.id_contact_account = :whomDeleteId",
            nativeQuery = true)
    void deleteUserFromContacts(@Param("whoDeleteId") UUID whoAddId, @Param("whomDeleteId") UUID whomAddId);
}
