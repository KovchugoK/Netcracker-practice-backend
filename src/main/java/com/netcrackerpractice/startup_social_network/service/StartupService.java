package com.netcrackerpractice.startup_social_network.service;

import com.netcrackerpractice.startup_social_network.dto.StartupDTO;
import com.netcrackerpractice.startup_social_network.entity.Startup;

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

    Startup saveStartup(Startup startup, String image);

    Startup updateStartup(UUID id, Startup startup, String image) throws GeneralSecurityException, IOException;
}
