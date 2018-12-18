package com.netcrackerpractice.startup_social_network.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResumeDTO {
    private UUID id;
    private String info;
    private BusinessRoleDTO businessRole;
    private Set<ResumeStartupDTO> startupResumes;
}
