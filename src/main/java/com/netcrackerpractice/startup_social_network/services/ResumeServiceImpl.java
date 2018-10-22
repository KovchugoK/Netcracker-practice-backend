package com.netcrackerpractice.startup_social_network.services;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public Optional<Resume> findById(Long resumeId) {
        return resumeRepository.findById(resumeId);
    }
}
