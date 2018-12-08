package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
    @Query(value = "SELECT * FROM conversation c WHERE c.id_your_account=:userId", nativeQuery = true)
    List<Conversation> getUserConversationsById(@Param("userId") UUID userId);

    @Query(value = "SELECT * FROM conversation c WHERE c.id_your_account=:yourId AND c.id_other_account=:otherId", nativeQuery = true)
    Optional<Conversation> getConversationIdByUsersIds(@Param("yourId") UUID yourId, @Param("otherId") UUID otherId);

    @Query(value = "INSERT INTO conversation(id_your_account, id_other_account, name) " +
            "VALUES (?1,?2,?3)", nativeQuery = true)
    @Modifying
    @Transactional
    void addConversation(UUID yourId, UUID otherId, String name);

    Optional<Conversation> findConversationById(UUID conversationId);
}
