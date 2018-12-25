package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.Startup;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StartupService {
    List<Startup> findAll();

    List<StartupDTO> searchStartups(String nameContains, String creatorContains, String sortBy, String sortDirection, String accountID);

    Optional<Startup> findStartupById(UUID id);

    void deleteStartupById(UUID id);

    ResponseEntity<?> saveStartup(Startup startup, String image);

    ResponseEntity<?> updateStartup(UUID id, Startup startup, String image);

    Boolean checkPermissionToEditStartup(UUID accountId, UUID startupId);

    void blockStartup(UUID id);
    void unBlockStartup(UUID id);
}
