package com.netcrackerpractice.startup_social_network.DTO;

import lombok.*;

import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupDTO {

    private UUID id;

    private String startupName;

    private String idea;

    private String aboutProject;

    private String businessPlan;

    private int sumOfInvestment;

    private Set<StartupResumeDTO> startupResumes;

    private String imageId;

    private String compressedImageId;

    private String image;

}
