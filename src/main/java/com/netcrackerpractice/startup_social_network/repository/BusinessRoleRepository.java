package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BusinessRoleRepository extends JpaRepository<BusinessRole, UUID> {
    BusinessRole findBusinessRoleByBusinessRoleName(BusinessRoleEnum businessRoleEnum);
    @Query(value = "SELECT * FROM business_roles WHERE business_roles.role_name != 'INVESTOR'" ,nativeQuery = true)
    List<BusinessRole> findBusinessRoleSpecialists();


}
