package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.repository.StartupRepository;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class StartupServiceImpl implements StartupService {
    @Autowired
    StartupRepository startupRepository;

    @Autowired
    ImageService imageService;

    @Override
    public List<Startup> findAll() {
        return startupRepository.findAll();
    }

    @Override
    public Optional<Startup> findStartupById(UUID id) {
        return startupRepository.findById(id);
    }

    @Override
    public void deleteStartupById(UUID id) {
        startupRepository.deleteById(id);
    }

    @Override
    public Startup saveStartup(Startup startup) {
        return startupRepository.save(startup);
    }


    @Override
    public Startup updateStartup(UUID id, Startup startup) {
        Optional<Startup> startupData = findStartupById(id);
        if (startupData.isPresent()) {
            Startup _startup = startupData.get();
            _startup.setStartupName(startup.getStartupName());
            _startup.setIdea(startup.getIdea());
            _startup.setSumOfInvestment(startup.getSumOfInvestment());
            _startup.setAboutProject(startup.getAboutProject());
            _startup.setBusinessPlan(startup.getBusinessPlan());
            return saveStartup(_startup);
        }
        return null;
    }

    @Override
    public void saveImages(MultipartFile image, Startup startup) throws IOException, GeneralSecurityException {
        File imageFile = imageService.convertMultipartToFile(image);
        String imageId = imageService.saveImageToGoogleDrive(imageFile);

        String comressedImagePath = imageService.compressionImage(imageFile);
        File comressedImageFile = new File(comressedImagePath);
        String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);

        startup.setImageId(imageId);
        startup.setCompressedImageId(comressedImageId);
        startupRepository.save(startup);

        imageFile.delete();
        comressedImageFile.delete();
    }
}
