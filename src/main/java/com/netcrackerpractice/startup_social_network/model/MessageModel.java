package com.netcrackerpractice.startup_social_network.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageModel {

    private UUID id;
    private UUID senderId;
    private UUID receiverId;
    private String msg;
    private Timestamp creationDate;
}
