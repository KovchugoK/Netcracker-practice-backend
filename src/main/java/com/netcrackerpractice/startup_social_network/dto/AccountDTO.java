package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Account;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Елизавета on 13.11.2018.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO  {

    private UUID id;

    private String firstName;

    private String secondName;

    private Date birthday;

    private String email;

    private String aboutMe;

    private List<ResumeDTO> resumes;

    private List<StartupDTO> startups;

    //private List<StartupRole> startupRoles;

    //private List<Favorite> favorites;

    private Set<EducationDTO> educations;

    private List<WorkExperienceDTO> workExperiences;

    public AccountDTO(Account account){
        this.id=account.getId();
        this.firstName=account.getFirstName();
        this.secondName=account.getSecondName();
        this.birthday=account.getBirthday();
        this.email=account.getEmail();
        this.aboutMe=account.getAboutMe();
        this.resumes=ResumeDTO.getListOfResumeDTO(account.getResumes());
        this.startups= StartupDTO.getListOfStartupDTO(account.getStartups());
        this.educations=EducationDTO.getSetOfEducationDTO(account.getEducations());
        this.workExperiences=WorkExperienceDTO.getListOfWorkExperienceDTO(account.getWorkExperiences());
    }



}