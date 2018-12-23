package com.netcrackerpractice.startup_social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupRoleDTO {
    private UUID id;
    private UUID startupId;
    private UUID accountId;
    private String roleName;

}

//public class StartupRoleDTO {
//    private UUID id;
//    private StartupDTO startup;
//}
