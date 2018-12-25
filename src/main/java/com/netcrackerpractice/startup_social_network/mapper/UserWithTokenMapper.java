package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.UserDTOwithToken;
import com.netcrackerpractice.startup_social_network.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserWithTokenMapper {

    UserDTOwithToken entityToDto(User user);

    @InheritInverseConfiguration
    User dtoToEntity(UserDTOwithToken user);

}