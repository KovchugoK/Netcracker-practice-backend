package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.DTO.ConversationDTO;
import com.netcrackerpractice.startup_social_network.exception.ConversationNotFoundException;
import com.netcrackerpractice.startup_social_network.mapper.ConversationMapper;
import com.netcrackerpractice.startup_social_network.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
