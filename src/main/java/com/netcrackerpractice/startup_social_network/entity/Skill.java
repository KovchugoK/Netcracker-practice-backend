package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "skill_name")
    private String skillName;

    @OneToMany(mappedBy = "skill")
    @JsonIgnoreProperties(value = "skill", allowSetters = true)
    private Set<ResumeSkill> resumeSkills;

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || super.getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        return id.equals(skill.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
