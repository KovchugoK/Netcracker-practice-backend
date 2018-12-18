package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.ResumeStartupDTO;
import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.dto.StartupResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.entity.StartupResume;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = "spring",
        uses = {ResumeMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StartupResumeMapper {

//       @Mapping( target = "resume.account.birthday", ignore=true)
//       @Mapping( target = "resume.account.user", ignore=true)
//       @Mapping( target = "resume.account.aboutMe", ignore=true)
//       @Mapping( target = "resume.account.resumes", ignore=true)
//       @Mapping( target = "resume.account.startups", ignore=true)
//       @Mapping( target = "resume.account.startupRoles", ignore=true)
//       @Mapping( target = "resume.account.favorites", ignore=true)
//       @Mapping( target = "resume.account.educations", ignore=true)
//       @Mapping( target = "resume.account.workExperiences", ignore=true)
//       @Mapping( target = "resume.account.imageId", ignore=true)
//       @Mapping( target = "resume.account.compressedImageId", ignore=true)
//       @Mapping( target = "resume.account.image", ignore=true)
       StartupResumeDTO entityToDto(StartupResume startupResume);

       @InheritInverseConfiguration
       StartupResume dtoToEntity(StartupResumeDTO startupResumeDTO);
//       StartupResumeDTO startupResumeToDto(StartupResume startupResume) {
//              StartupResumeDTO startupResumeDTO = StartupResumeDTO.builder()
//                      .id(startupResume.getId())
//                      .resumeId(startupResume.getResume().getId())
//                      .build();
//              return startupResumeDTO;
//       }
//
//       ResumeStartupDTO resumeStartupToDto(StartupResume startupResume) {
//              ResumeStartupDTO resumeStartupDTO = ResumeStartupDTO.builder()
//                      .id(startupResume.getId())
//                      .status(startupResume.getStatus())
//                      .startupId(startupResume.getStartup().getId())
//                      .startupName(startupResume.getStartup().getStartupName())
//                      .idea(startupResume.getStartup().getIdea())
//                      .aboutProject(startupResume.getStartup().getAboutProject())
//                      .businessPlan(startupResume.getStartup().getBusinessPlan())
//                      .sumOfInvestment(startupResume.getStartup().getSumOfInvestment())
//                      .build();
//              return resumeStartupDTO;
//       }
}


