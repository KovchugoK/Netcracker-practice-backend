package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.repository.StartupRepository;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class StartupServiceImpl implements StartupService {
    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private ImageService imageService;

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
    public Startup updateStartup(UUID id, Startup startup, String image) throws GeneralSecurityException, IOException {
        Optional<Startup> startupData = findStartupById(id);
        if (startupData.isPresent()) {
            Startup _startup = startupData.get();
            _startup.setStartupName(startup.getStartupName());
            _startup.setIdea(startup.getIdea());
            _startup.setSumOfInvestment(startup.getSumOfInvestment());
            _startup.setAboutProject(startup.getAboutProject());
            _startup.setBusinessPlan(startup.getBusinessPlan());


            imageService.deleteImageFromGoogleDrive(_startup.getImageId(), _startup.getCompressedImageId());
            File imageFile = imageService.convertStringToFile(image);
            String imageId = imageService.saveImageToGoogleDrive(imageFile);

            String comressedImagePath = imageService.compressionImage(imageFile);
            File comressedImageFile = new File(comressedImagePath);
            String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);

            _startup.setImageId(imageId);
            _startup.setCompressedImageId(comressedImageId);
            startupRepository.save(startup);

            imageFile.delete();
            comressedImageFile.delete();
            return saveStartup(_startup);
        }
        return null;
    }


    @Override
    public List<Startup> searchStartups(String nameContains, String creatorContains, String sortBy, String sortDirection, String accountID) {

        if(nameContains == null){
            nameContains = "";
        }
        if(creatorContains == null){
            creatorContains = "";
        }

        if(sortBy == null || !(sortBy.equals("startupName")  || sortBy.equals("sumOfInvestment") || sortBy.equals("dateOfCreation") )){
            sortBy = "startupName";
        }
        if(sortDirection == null || !(sortDirection.toUpperCase().equals("ASC") || sortDirection.toUpperCase().equals("DESC"))){
            sortDirection = "ASC";
        }

        List<Startup> startupList = new ArrayList<>();

        if(accountID != null && !accountID.equals("")){
            startupList.addAll(startupRepository.searchStartupAsLeader
                    (       nameContains.trim().toLowerCase(),
                            creatorContains.trim().toLowerCase(),
                            new Sort(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortBy)
                    ));

            try {
                startupList.addAll(startupRepository.searchStartupsAsDeveloper
                        (UUID.fromString(accountID),
                                nameContains.trim().toLowerCase(),
                                new Sort(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortBy)
                        ));
            }
            catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        }
        else {
            startupList.addAll(startupRepository.searchAllStartups
                    (       nameContains.trim().toLowerCase(),
                            creatorContains.trim().toLowerCase(),
                            new Sort(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortBy)
                    ));
        }

        return startupList;

    }

}
