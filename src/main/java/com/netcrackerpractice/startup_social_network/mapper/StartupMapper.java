package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.DTO.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        uses = {ResumeMapper.class, AccountMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StartupMapper {

    @Mapping(target = "image", ignore=true)
    StartupDTO entityToDto(Startup startup);

    @InheritInverseConfiguration
    Startup dtoToEntity(StartupDTO startupDTO);
}
