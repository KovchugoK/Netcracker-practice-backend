package com.netcrackerpractice.startup_social_network.DTO;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupResumeDTO {

    private UUID id;

    private UUID resumeId;

}
