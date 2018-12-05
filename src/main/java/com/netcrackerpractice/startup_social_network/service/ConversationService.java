package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Conversation;

import java.util.List;
import java.util.UUID;

public interface ConversationService {
    List<Conversation> getUserConversationsById(UUID userId);
}
