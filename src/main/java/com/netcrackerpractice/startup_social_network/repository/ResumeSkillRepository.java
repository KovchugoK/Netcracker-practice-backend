package com.netcrackerpractice.startup_social_network.repository;

import com.netcrackerpractice.startup_social_network.entity.Resume;
import com.netcrackerpractice.startup_social_network.entity.ResumeSkill;
import com.netcrackerpractice.startup_social_network.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface ResumeSkillRepository  extends JpaRepository<ResumeSkill, UUID> {
    Set<ResumeSkill> findResumeSkillByResume(Resume resume);
    Set<ResumeSkill> findResumeSkillBySkill(Skill skill);
}
