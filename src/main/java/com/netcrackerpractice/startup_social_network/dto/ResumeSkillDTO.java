package com.netcrackerpractice.startup_social_network.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResumeSkillDTO {
    private UUID id;
    private SkillDTO skill;
}
