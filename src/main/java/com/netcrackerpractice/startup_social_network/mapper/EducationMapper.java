package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.EducationDTO;
import com.netcrackerpractice.startup_social_network.entity.Education;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {AccountMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper {

    @Mapping( target = "account.educations", ignore = true)
    @Mapping( target = "account.workExperiences", ignore = true)
    EducationDTO toDtoWithoutAccount(Education education);

    @InheritInverseConfiguration
    Education dtoToEntity(EducationDTO educationDTO);
}
