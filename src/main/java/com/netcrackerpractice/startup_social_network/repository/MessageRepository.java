package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query(value = "SELECT * FROM message m WHERE m.conversation_id=:conversationId", nativeQuery = true)
    List<Message> getConversationMessagesById(@Param("conversationId") UUID conversationId);
}
