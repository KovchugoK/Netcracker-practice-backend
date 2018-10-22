package com.netcrackerpractice.startup_social_network.services;

import com.netcrackerpractice.startup_social_network.entity.Resume;

import java.util.Optional;

public interface ResumeService {
    Optional<Resume> findById(Long resumeId);
}
