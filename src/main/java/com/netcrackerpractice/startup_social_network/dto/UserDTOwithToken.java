package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.payload.JwtAuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTOwithToken {
    private UUID id;
    private String login;
    private String hashedPassword;
    private String salt;
    private String email;
    private boolean nonBlock;
    private Collection<RoleDTO> roles;
    private DetailAccountDTO account;
    private JwtAuthenticationResponse token;
}
