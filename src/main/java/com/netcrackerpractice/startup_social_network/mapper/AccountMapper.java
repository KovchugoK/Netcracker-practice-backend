package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.DTO.DetailAccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {ResumeMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    DetailAccountDTO entityToDto(Account account);

    @InheritInverseConfiguration
    Account dtoToEntity(DetailAccountDTO accountDTO);

}
