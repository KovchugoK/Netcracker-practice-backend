package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.ResumeDTO;
import com.netcrackerpractice.startup_social_network.entity.BusinessRole;
import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.Skill;
import com.netcrackerpractice.startup_social_network.mapper.ResumeMapper;
import com.netcrackerpractice.startup_social_network.repository.BusinessRoleRepository;
import com.netcrackerpractice.startup_social_network.repository.SkillRepository;
import com.netcrackerpractice.startup_social_network.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private ResumeMapper resumeMapper;

    @GetMapping("/list")
    public List<ResumeDTO> listAllResumes() {
        List<ResumeDTO> resumeDTOS = new ArrayList<>();
        resumeService.listAllResumes().forEach(resume -> resumeDTOS.add(resumeMapper.entityToDto(resume)));
        return resumeDTOS;
    }


    @GetMapping("/{id}")
    public ResumeDTO geResumeById(@PathVariable UUID id) {
        return resumeMapper.entityToDto(resumeService.getResumeById(id));
    }

    @GetMapping("/skills")
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @GetMapping("/businessRole")
    public List<BusinessRole> getAllBusinessRole() {
        return businessRoleRepository.findAll();
    }

    @GetMapping("/specialists-business-role")
    public List<BusinessRole> getSpecialistsBusinessRole() {
        return businessRoleRepository.findBusinessRoleSpecialists();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteResume(@PathVariable UUID id) {
        resumeService.deleteResumeById(id);
    }

    @PostMapping("/create")
    public Resume saveResume(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @PutMapping("/update/{id}")
    public Resume updateResume(@PathVariable UUID id, @RequestBody Resume resume) {
        return resumeService.updateResume(id, resume);
    }

    @GetMapping("/my-resume-list/{id}")
    public List<ResumeDTO> findMyResumeList(@PathVariable UUID id) {
        List<ResumeDTO> resumeDTOS = new ArrayList<>();
        resumeService.findResumesByAccountId(id).forEach(resume -> resumeDTOS.add(resumeMapper.entityToDto(resume)));
        return resumeDTOS;
    }


}
