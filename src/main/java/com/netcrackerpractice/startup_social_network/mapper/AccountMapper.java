package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.DTO.DetailAccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {ResumeMapper.class,EducationMapper.class,WorkExperienceMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {


    @Mapping( target = "image", ignore=true)
    DetailAccountDTO entityToDto(Account account);

    @InheritInverseConfiguration
    Account dtoToEntity(DetailAccountDTO accountDTO);

}
