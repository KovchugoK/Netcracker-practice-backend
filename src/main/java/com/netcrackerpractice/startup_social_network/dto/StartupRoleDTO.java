package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.entity.StartupRole;
import lombok.*;

import java.util.Set;
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
