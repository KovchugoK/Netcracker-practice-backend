package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Conversation;
import com.netcrackerpractice.startup_social_network.repository.ConversationRepository;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public List<Conversation> getUserConversationsById(UUID userId) {
        return conversationRepository.getUserConversationsById(userId);
    }

    @Override
    public Optional<Conversation> getConversationByUsersIds(UUID yourId, UUID otherId) {
        Optional<Conversation> conversationOptional = conversationRepository.getConversationByUsersIds(yourId, otherId);
        if (!conversationOptional.isPresent()) {
            conversationRepository.addConversation(yourId, otherId, accountService.findAccountById(otherId).get().getLastName());
        }
        return conversationRepository.getConversationByUsersIds(yourId, otherId);
    }

    @Override
    public void addConversation(UUID yourId, UUID otherId, String name) {
        conversationRepository.addConversation(yourId, otherId, name);
    }

    @Override
    public Optional<Conversation> findConversationById(UUID conversationId) {
        return conversationRepository.findConversationById(conversationId);
    }
}
