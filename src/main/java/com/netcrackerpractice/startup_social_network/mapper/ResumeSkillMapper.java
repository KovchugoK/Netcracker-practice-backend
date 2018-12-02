package com.netcrackerpractice.startup_social_network.mapper;

import com.netcrackerpractice.startup_social_network.dto.ResumeSkillDTO;
import com.netcrackerpractice.startup_social_network.entity.ResumeSkill;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Created by Елизавета on 29.11.2018.
 */
@Mapper(componentModel = "spring", uses = {SkillMapper.class})
public interface ResumeSkillMapper {

    ResumeSkillDTO entityToDTO(ResumeSkill startupResume);

    @InheritInverseConfiguration
    ResumeSkill dtoToEntity(ResumeSkillDTO startupResume);

}
