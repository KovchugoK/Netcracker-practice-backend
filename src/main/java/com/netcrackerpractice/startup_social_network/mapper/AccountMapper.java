package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Created by Елизавета on 18.11.2018.
 */
@Mapper(componentModel = "spring", uses = {AccountService.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    AccountDTO entityToDto(Account account);


    @InheritInverseConfiguration
    Account dtoToEntity(AccountDTO accountDTO);

}
