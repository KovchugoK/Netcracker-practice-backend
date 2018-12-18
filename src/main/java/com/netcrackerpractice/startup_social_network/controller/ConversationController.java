package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.ConversationDTO;
import com.netcrackerpractice.startup_social_network.exception.ConversationNotFoundException;
import com.netcrackerpractice.startup_social_network.mapper.ConversationMapper;
import com.netcrackerpractice.startup_social_network.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/conversation")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;
    @Autowired
    private ConversationMapper conversationMapper;

    @GetMapping("/getConversationInfoByUsersIds")
    public ConversationDTO getConversationInfo(@RequestParam(name = "yourId") UUID yourId, @RequestParam(name = "otherId") UUID otherId) {
        return conversationMapper.conversationToConversationDTO(
                conversationService.getConversationByUsersIds(yourId, otherId).orElseThrow(
                        () -> new ConversationNotFoundException("Conversation not found for users: " + yourId + "and " + otherId)
                ));
    }

    @GetMapping("/{userId}")
    public List<ConversationDTO> getConversationInfoById(@PathVariable(name = "userId") UUID userId) {
        return conversationMapper.conversationDTOtoConversation(conversationService.getUserConversationsById(userId));
    }
}
