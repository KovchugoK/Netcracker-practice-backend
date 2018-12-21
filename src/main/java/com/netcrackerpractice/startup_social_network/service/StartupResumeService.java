package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.dto.StartupResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.StartupResume;

import java.util.UUID;

public interface StartupResumeService {
    StartupResumeDTO addStartupResume(StartupResumeDTO startupResume);
    void deleteStartupResumeById(UUID id);
    StartupResumeDTO acceptStartupResume(UUID startupResumeId, String startupRole);
}
