package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.StartupResume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Елизавета on 15.11.2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartupResumeDTO {

    private UUID id;

    private String status;

    private UUID startupId;

    private String startupName;

    StartupResumeDTO(StartupResume startupResume){
        this.id=startupResume.getId();
        this.status=startupResume.getStatus();
        this.startupId=startupResume.getStartup().getId();
        this.startupName=startupResume.getStartup().getStartupName();
    }

    public static Set<StartupResumeDTO> getSetOfStartupResumeDTO(Set<StartupResume> startupResumeSet){
        Set<StartupResumeDTO> startupResumeDTOSet=new HashSet<>();
        startupResumeSet.forEach(startupResume -> {startupResumeDTOSet.add(new StartupResumeDTO(startupResume));});
        return startupResumeDTOSet;
    }

}
