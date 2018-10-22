package com.netcrackerpractice.startup_social_network.repositories;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Long> {

}
