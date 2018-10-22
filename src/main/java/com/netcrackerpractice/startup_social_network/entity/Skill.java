package com.netcrackerpractice.startup_social_network.entity;

import com.netcrackerpractice.startup_social_network.annotations.IgnoreDuringScan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "skill_name")
    private String skillName;

    @OneToMany(mappedBy = "skill")
    Set<ResumeSkill> resumeSkills;
}
