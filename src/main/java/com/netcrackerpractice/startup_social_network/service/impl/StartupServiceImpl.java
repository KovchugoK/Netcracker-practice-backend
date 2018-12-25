package com.netcrackerpractice.startup_social_network.service.impl;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.repository.StartupRepository;
import com.netcrackerpractice.startup_social_network.repository.StartupRoleRepository;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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

    @Autowired
    StartupRoleRepository startupRoleRepository;


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
    public Startup saveStartup(Startup startup, String image) {
        if (startupRepository.findByStartupName(startup.getStartupName()).isPresent()) {
            return null;
        }
        if (image != null && !image.equals("")) {
            try {
                File imageFile = imageService.convertStringToFile(image);
                String imageId = imageService.saveImageToGoogleDrive(imageFile);
                String comressedImagePath = imageService.compressionImage(imageFile);
                File comressedImageFile = new File(comressedImagePath);
                String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);
                startup.setImageId(imageId);
                startup.setCompressedImageId(comressedImageId);
                imageFile.delete();
                comressedImageFile.delete();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return startupRepository.save(startup);
    }


    @Override
    public Startup updateStartup(UUID id, Startup startup, String image) {
        if (!startupRepository.findById(id).isPresent()) {
            return null;
        }
        Optional<Startup> st = startupRepository.findByStartupName(startup.getStartupName());
        if (st.isPresent()) {
            if (startup.getId() == null || !st.get().getId().equals(id)) {
                return null;
            }
        }
        if (image != null && !image.equals("")) {
            try {
                if (startup.getImageId() != null && startup.getCompressedImageId() != null) {
                    imageService.deleteImageFromGoogleDrive(startup.getImageId(), startup.getCompressedImageId());
                }
                File imageFile = imageService.convertStringToFile(image);
                String imageId = imageService.saveImageToGoogleDrive(imageFile);

                String comressedImagePath = imageService.compressionImage(imageFile);
                File comressedImageFile = new File(comressedImagePath);
                String comressedImageId = imageService.saveImageToGoogleDrive(comressedImageFile);

                startup.setImageId(imageId);
                startup.setCompressedImageId(comressedImageId);
                imageFile.delete();
                comressedImageFile.delete();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return startupRepository.save(startup);
    }

    @Override
    public List<Startup> searchStartups(String nameContains, String creatorContains, String sortBy, String sortDirection, String accountID) {

        if (nameContains == null) {
            nameContains = "";
        }
        if (creatorContains == null) {
            creatorContains = "";
        }

        if (sortBy == null || !(sortBy.equals("startupName") || sortBy.equals("sumOfInvestment") || sortBy.equals("dateOfCreation"))) {
            sortBy = "startupName";
        }
        if (sortDirection == null || !(sortDirection.toUpperCase().equals("ASC") || sortDirection.toUpperCase().equals("DESC"))) {
            sortDirection = "ASC";
        }

        List<Startup> startupList = new ArrayList<>();

        if (accountID != null && !accountID.equals("")) {
            startupList.addAll(startupRepository.searchStartupAsLeader
                    (nameContains.trim().toLowerCase(),
                            creatorContains.trim().toLowerCase(),
                            new Sort(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortBy)
                    ));

            try {
                startupList.addAll(startupRepository.searchStartupsAsMember
                        (UUID.fromString(accountID),
                                nameContains.trim().toLowerCase(),
                                new Sort(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortBy)
                        ));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            startupList.addAll(startupRepository.searchAllStartups
                    (nameContains.trim().toLowerCase(),
                            creatorContains.trim().toLowerCase(),
                            new Sort(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortBy)
                    ));
        }

        return startupList;

    }

    @Override
    public Boolean checkPermissionToEditStartup(UUID accountId, UUID startupId) {
        return this.startupRoleRepository.findModeratorInStartup(accountId, startupId).isPresent()
                || this.startupRepository.findStartupByIdAndAccountId(startupId, accountId).isPresent();
    }

    @Override
    public void blockStartup(UUID id) {
        startupRepository.blockStartup(id);
    }

    @Override
    public void unBlockStartup(UUID id) {
        startupRepository.unBlockStartup(id);
    }
}
