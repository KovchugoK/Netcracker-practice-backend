package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * Created by Елизавета on 14.11.2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResumeDTO {

    private UUID id;

    private String info;

    private BusinessRoleDTO businessRole;

    private Set<SkillDTO> resumeSkills;

    private Set<StartupResumeDTO> startupResumes;

    public ResumeDTO(Resume resume){
        this.id=resume.getId();
        this.info=resume.getInfo();
        this.businessRole= new BusinessRoleDTO(resume.getBusinessRole());
        this.resumeSkills=getListOfSkillDTOFromResume(resume);
        this.startupResumes=StartupResumeDTO.getSetOfStartupResumeDTO(resume.getStartupResumes());
    }

    public Set<SkillDTO> getListOfSkillDTOFromResume(Resume resume){
        Set<SkillDTO> skillDTOList=new HashSet<>();
        resume.getResumeSkills().forEach(resumeSkill -> {
            skillDTOList.add(new SkillDTO(resumeSkill.getSkill()));
        });
        return skillDTOList;
    }

    static public List<ResumeDTO> getListOfResumeDTO(List<Resume> resumeList){
        List<ResumeDTO> resumeDTOList=new ArrayList<>();
        resumeList.forEach(resume -> {resumeDTOList.add(new ResumeDTO(resume));});
        return resumeDTOList;
    }
}
