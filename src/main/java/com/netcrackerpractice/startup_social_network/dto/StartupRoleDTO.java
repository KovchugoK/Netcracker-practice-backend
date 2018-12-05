package com.netcrackerpractice.startup_social_network.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupRoleDTO {

    private UUID id;

    private StartupDTO startup;

}
