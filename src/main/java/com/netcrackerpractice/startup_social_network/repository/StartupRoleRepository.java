package com.netcrackerpractice.startup_social_network.repository;


import com.netcrackerpractice.startup_social_network.entity.StartupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StartupRoleRepository  extends JpaRepository<StartupRole, UUID> {

}
