package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.WorkExperience;
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
public class WorkExperienceDTO {

    private String workPlace;

    private Date start;

    private Date finish;

    private String position;

    public WorkExperienceDTO(WorkExperience workExperiences){
        this.workPlace=workExperiences.getWorkPlace();
        this.start=workExperiences.getStart();
        this.finish=workExperiences.getFinish();
        this.position=workExperiences.getPosition();

    }
    static public List<WorkExperienceDTO> getListOfWorkExperienceDTO(List<WorkExperience> workExperienceList){
        List<WorkExperienceDTO> workExperienceDTOList=new ArrayList<>();
        workExperienceList.forEach(workExperience -> {workExperienceDTOList.add(new WorkExperienceDTO(workExperience));});
        return workExperienceDTOList;
    }
}
