package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.WorkExperienceDTO;
import com.netcrackerpractice.startup_social_network.entity.WorkExperience;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {AccountMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkExperienceMapper {

    @Mapping(target = "account.workExperiences", ignore = true)
    @Mapping(target = "account.educations", ignore = true)
    WorkExperienceDTO toDtoWithoutAccount(WorkExperience city);

    @InheritInverseConfiguration
    WorkExperience dtoToEntity(WorkExperienceDTO accountDTO);
}
