package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.WorkExperience;
import com.netcrackerpractice.startup_social_network.repository.WorkExperienceRepository;
import com.netcrackerpractice.startup_social_network.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Override
    public void deleteWorkExperienceById(UUID id) {
        workExperienceRepository.deleteById(id);
    }

    @Override
    public WorkExperience saveWorkExperience(WorkExperience workExperience) {
        return workExperienceRepository.save(workExperience);
    }

    @Override
    public WorkExperience updateWorkExperience(UUID id, WorkExperience workExperience) {
        WorkExperience _workExperience=workExperienceRepository.findById(id).get();
        _workExperience.setId(workExperience.getId());
        _workExperience.setWorkPlace(workExperience.getWorkPlace());
        _workExperience.setPosition(workExperience.getPosition());
        _workExperience.setStart(workExperience.getStart());
        _workExperience.setFinish(workExperience.getFinish());
        workExperienceRepository.save(_workExperience);
        return null;
    }
}
