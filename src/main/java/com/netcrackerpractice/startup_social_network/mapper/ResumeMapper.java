package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.DTO.ResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {StartupResumeMapper.class})
public interface ResumeMapper {

    ResumeDTO entityToDto(Resume resume);

    @InheritInverseConfiguration
    Resume dtoToEntity(ResumeDTO resumeDTO);

}
