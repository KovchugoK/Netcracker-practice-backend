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
public class EducationDTO {

        private UUID id;
              
        private String institution;
       
        private int completionYear;

        private DetailAccountDTO account;
}