package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Created by Елизавета on 14.11.2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillDTO {

    private UUID id;

    private String skillName;

    public SkillDTO(Skill skill){
        this.id=skill.getId();
        this.skillName=skill.getSkillName();
    }
}
