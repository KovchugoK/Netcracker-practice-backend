package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.StartupResumeDTO;
import com.netcrackerpractice.startup_social_network.service.StartupResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/startup-resume")
public class StartupResumeController {

    @Autowired
    private StartupResumeService startupResumeService;

    @PostMapping("")
    public StartupResumeDTO addStartupResume(@RequestBody StartupResumeDTO startupResume) {
        return startupResumeService.addStartupResume(startupResume);
    }

    @PutMapping("/accept-resume/{id}")
    public StartupResumeDTO acceptStartupResume(@PathVariable(name = "id") UUID id, @RequestBody String startupRole) {
        return startupResumeService.acceptStartupResume(id, startupRole);
    }

    @DeleteMapping("/cancel-resume/{id}")
    public void rejectResume(@PathVariable(name = "id") UUID id) {
        startupResumeService.deleteStartupResumeById(id);
    }


}
