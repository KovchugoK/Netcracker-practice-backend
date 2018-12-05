package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.entity.*;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.repository.SkillRepository;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private BusinessRoleRepository businessRoleRepository;

    @GetMapping("/list")
    public List<Resume> listAllResumes() {
        return resumeService.listAllResumes();
    }


    @GetMapping("/{id}")
    public Optional<Resume> geResumeById(@PathVariable UUID id) {
        System.out.println(resumeService.getResumeById(id));
        return resumeService.getResumeById(id);
    }

    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @GetMapping("/businessRole")
    public List<BusinessRole> getAllBusinessRole() {
        return businessRoleRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteResume(@PathVariable UUID id) {
        resumeService.deleteResumeById(id);
    }

    @PostMapping("/create")
    public Resume saveResume(@RequestBody Resume resume) {
        System.out.println(resume);
        return resumeService.saveResume(resume);
    }

    @PutMapping("/update/{id}")
    public Resume updateResume(@PathVariable UUID id, @RequestBody Resume resume) {
        Resume resume1 = resumeService.updateResume(id, resume);
        System.out.println(resume1);
        return resume1;
    }

    @DeleteMapping("/{id}/delete/skill")
    public void deleteSkill(Skill skill, @PathVariable UUID id) {
        resumeService.deleteResumeSkill(id, skill);
    }

}
