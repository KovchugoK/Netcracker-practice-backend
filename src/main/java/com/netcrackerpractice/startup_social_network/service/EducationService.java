package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.entity.Education;
import java.util.UUID;

public interface EducationService {
    void deleteEducationById(UUID id);
    Education saveEducation(Education education);
    Education updateEducation(UUID id, Education education);
}
