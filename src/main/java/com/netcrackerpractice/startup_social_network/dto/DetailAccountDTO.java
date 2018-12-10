package com.netcrackerpractice.startup_social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailAccountDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private Date birthday;

    private UserDTO user;

    private String aboutMe;

    private List<ResumeDTO> resumes;

    private List<StartupDTO> startups;

    private List<StartupRoleDTO> startupRoles;

    private List<FavoriteDTO> favorites;

    private Set<EducationDTO> educations;

    private List<WorkExperienceDTO> workExperiences;

    private String imageId;

    private String compressedImageId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}