package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @GetMapping("/list")
    public ResponseEntity<List<Resume>> listAllResumes() {
        List<Resume> resumes = resumeService.listAllResumes();
        if(resumes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resumes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable final UUID id) {
        Resume resume = resumeService.getResumeById(id);
        if(resume == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }
}
