package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, UUID> {

}
