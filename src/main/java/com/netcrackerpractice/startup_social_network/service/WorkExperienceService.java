package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.WorkExperience;
import java.util.UUID;

public interface WorkExperienceService {
    void deleteWorkExperienceById(UUID id);
    WorkExperience saveWorkExperience(WorkExperience workExperience);
    WorkExperience updateWorkExperience(UUID id, WorkExperience workExperience);
}
