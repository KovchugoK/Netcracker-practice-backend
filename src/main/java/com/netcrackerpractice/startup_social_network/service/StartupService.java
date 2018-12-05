package com.netcrackerpractice.startup_social_network.service;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StartupService{
    List<Startup> findAll();
    List<Startup> searchStartups(String nameContains, String creatorContains, String sortBy, String sortDirection, String accountID);
    Optional<Startup> findStartupById(UUID id);
    void deleteStartupById(UUID id);
    Startup saveStartup(Startup startup);
    Startup updateStartup(UUID id, Startup startup);
    void saveImages(MultipartFile image, Startup startup) throws IOException, GeneralSecurityException;
}
