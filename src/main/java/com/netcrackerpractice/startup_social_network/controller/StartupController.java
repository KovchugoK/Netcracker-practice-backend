package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.entity.Startup;
import com.netcrackerpractice.startup_social_network.payload.SearchStartupsRequest;
import com.netcrackerpractice.startup_social_network.service.StartupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/startup")
public class StartupController {

    @Autowired
    StartupService startupService;

    @GetMapping("/startup-list")
    public List<Startup> getStartupList() {
        return startupService.findAll();
    }

    @GetMapping("/search-startups")
    public List<Startup> searchStartups(SearchStartupsRequest searchStartupsRequest) {
        return startupService.searchStartups(
                searchStartupsRequest.getStartupNameContains(),
                searchStartupsRequest.getCreator(),
                searchStartupsRequest.getSortBy(),
                searchStartupsRequest.getSortDirection(),
                searchStartupsRequest.getAccountID()
        );
    }

    @GetMapping("/{id}")
    public Optional<Startup> getStartupById(@PathVariable(name = "id") UUID id) {
        return startupService.findStartupById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStartup(@PathVariable(name = "id") UUID id) {
        startupService.deleteStartupById(id);
    }

    @PostMapping("/create")
    public Startup saveStartup(@RequestBody Startup startup) {
        System.out.println(startup);
        return startupService.saveStartup(startup);
    }

    @PutMapping("/update/{id}")
    public Startup updateStartup(@PathVariable(name = "id") UUID id, @RequestBody Startup startup) {
        return startupService.updateStartup(id, startup);
    }

    @PostMapping("/image")
    private ResponseEntity<?> saveImages(@RequestParam("image") MultipartFile image,
                                         @RequestParam(name = "Id") UUID startupId) {
        try {
            Optional<Startup> startupOptional = startupService.findStartupById(startupId);

            if (startupOptional.isPresent()) {
                Startup startup = startupOptional.get();
                startupService.saveImages(image, startup);
            } else
                return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAIL to upload. Did't find startup");

            return ResponseEntity.status(HttpStatus.OK).body("You successfully uploaded");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAIL to upload");
        }
    }

}