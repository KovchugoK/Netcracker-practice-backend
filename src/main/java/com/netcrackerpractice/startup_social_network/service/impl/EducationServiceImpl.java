package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Education;
import com.netcrackerpractice.startup_social_network.repository.EducationRepository;
import com.netcrackerpractice.startup_social_network.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    public EducationRepository educationRepository;

    @Override
    public void deleteEducationById(UUID id) {
        educationRepository.deleteById(id);
    }

    @Override
    public Education saveEducation(Education education) {
        return educationRepository.save(education);
    }

    @Override
    public Education updateEducation(Education education) {
        Education updatedEducation=educationRepository.findById(education.getId()).get();
        updatedEducation.setInstitution(education.getInstitution());
        updatedEducation.setCompletionYear(education.getCompletionYear());
        educationRepository.save(updatedEducation);
        return null;
    }
}
