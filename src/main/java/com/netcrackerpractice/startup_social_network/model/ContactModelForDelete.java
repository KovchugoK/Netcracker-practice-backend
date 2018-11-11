package com.netcrackerpractice.startup_social_network.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactModelForDelete {
    private UUID whoDeleteId;
    private UUID whomDeleteId;
}
