package com.netcrackerpractice.startup_social_network.controllers;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/resume/{resume-id}")
    public Resume getResume(@PathVariable("resume-id") Long resumeId) {
        return resumeService.findById(resumeId).get();
    }

}
