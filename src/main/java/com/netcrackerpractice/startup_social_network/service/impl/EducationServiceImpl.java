package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Education;
import com.netcrackerpractice.startup_social_network.repository.EducationRepository;
import com.netcrackerpractice.startup_social_network.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public Education updateEducation(UUID id, Education education) {
        Optional<Education> optional=educationRepository.findById(id);
        if(optional.isPresent()){
            Education _education=optional.get();
            _education.setId(education.getId());
            _education.setAccount(education.getAccount());
            _education.setCompletionYear(education.getCompletionYear());
            _education.setInstitution(education.getInstitution());
            educationRepository.save(_education);
        }
        return null;
    }
}
