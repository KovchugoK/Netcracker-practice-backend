package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Conversation;
import com.netcrackerpractice.startup_social_network.repository.ConversationRepository;
import com.netcrackerpractice.startup_social_network.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public List<Conversation> getUserConversationsById(UUID userId) {
        return conversationRepository.getUserConversationsById(userId);
    }
}
