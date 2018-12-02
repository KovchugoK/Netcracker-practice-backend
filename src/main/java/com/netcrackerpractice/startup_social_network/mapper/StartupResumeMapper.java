package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.DTO.ResumeStartupDTO;
import com.netcrackerpractice.startup_social_network.DTO.StartupResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.StartupResume;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
class StartupResumeMapper {

       StartupResumeDTO startupResumeToDto(StartupResume startupResume) {
              StartupResumeDTO startupResumeDTO = StartupResumeDTO.builder()
                      .id(startupResume.getId())
                      .resumeId(startupResume.getResume().getId())
                      .build();
              return startupResumeDTO;
       }

       ResumeStartupDTO resumeStartupToDto(StartupResume startupResume) {
              ResumeStartupDTO resumeStartupDTO = ResumeStartupDTO.builder()
                      .id(startupResume.getId())
                      .status(startupResume.getStatus())
                      .startupId(startupResume.getStartup().getId())
                      .startupName(startupResume.getStartup().getStartupName())
                      .idea(startupResume.getStartup().getIdea())
                      .aboutProject(startupResume.getStartup().getAboutProject())
                      .businessPlan(startupResume.getStartup().getBusinessPlan())
                      .sumOfInvestment(startupResume.getStartup().getSumOfInvestment())
                      .build();
              return resumeStartupDTO;
       }
}


