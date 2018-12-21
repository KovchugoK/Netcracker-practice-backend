package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Investment;
import com.netcrackerpractice.startup_social_network.entity.StartupRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupDTO {
    private UUID id;
    private String startupName;
    private Account account;
    private String idea;
    private String aboutProject;
    private String businessPlan;
    private int sumOfInvestment;
    private Set<StartupResumeDTO> startupResumes;
    private String imageId;
    private String compressedImageId;
    private String image;
    private Set<Investment> startupInvestments;
    private Set<StartupRoleDTO> startupRoles = new HashSet<>();
    private boolean nonBlock;

    public Set<StartupRoleDTO> getStartupRoles() {
        return startupRoles;
    }

    public void setStartupRoles(Set<StartupRoleDTO> startupRoles) {
       if(startupRoles != null && startupRoles.size() != 0) {
           this.startupRoles.clear();
           this.startupRoles.addAll(startupRoles);
       }
    }
}
