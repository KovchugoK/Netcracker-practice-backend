package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Created by Елизавета on 14.11.2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessRoleDTO {

    private UUID id;

    private BusinessRoleEnum businessRoleName;

    public BusinessRoleDTO(BusinessRole businessRole){
        this.id=businessRole.getId();
        this.businessRoleName=businessRole.getBusinessRoleName();
    }
}
