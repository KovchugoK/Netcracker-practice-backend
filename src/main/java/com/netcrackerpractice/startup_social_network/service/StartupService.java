package com.netcrackerpractice.startup_social_network.service;
import com.netcrackerpractice.startup_social_network.entity.Startup;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StartupService{
    List<Startup> findAll();
    Optional<Startup> findStartupById(UUID id);
    void deleteStartupById(UUID id);
    Startup saveStartup(Startup startup);
    Startup updateStartup(UUID id, Startup startup);
}
