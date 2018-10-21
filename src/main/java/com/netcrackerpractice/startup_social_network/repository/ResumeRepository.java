package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
