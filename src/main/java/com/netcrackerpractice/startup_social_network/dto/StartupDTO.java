package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * Created by Елизавета on 14.11.2018.
 */
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

    private Set<ResumeDTO> startupResumes;

    public StartupDTO(Startup startup){
        this.id=startup.getId();
        this.startupName=startup.getStartupName();
        this.idea=startup.getIdea();
        this.aboutProject=startup.getAboutProject();
        this.businessPlan=startup.getBusinessPlan();
        this.sumOfInvestment=startup.getSumOfInvestment();
        this.startupResumes=getSetOfResumeDTOFromStartup(startup);

    }

    public static List<StartupDTO> getListOfStartupDTO(List<Startup> startupList){
        List<StartupDTO> startupDTOList=new ArrayList<>();
        startupList.forEach(startup -> {startupDTOList.add(new StartupDTO(startup));});
        return startupDTOList;
    }

    public Set<ResumeDTO> getSetOfResumeDTOFromStartup(Startup startup){
        Set<ResumeDTO> resumeDTOSet=new HashSet<>();
        startup.getStartupResumes().forEach(startupResume -> {
            resumeDTOSet.add(new ResumeDTO(startupResume.getResume()));
        });
        return resumeDTOSet;
    }
}
