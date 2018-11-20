package com.netcrackerpractice.startup_social_network.dto;

import com.netcrackerpractice.startup_social_network.entity.Education;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Елизавета on 14.11.2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EducationDTO {
              
        private String institution;
       
        private int completionYear;
        
      public EducationDTO(Education education){
         this.completionYear=education.getCompletionYear();
         this.institution=education.getInstitution();
      }
    static public Set<EducationDTO> getSetOfEducationDTO(Set<Education> educationSet){
        Set<EducationDTO> educationDTOSet=new HashSet<>();
        educationSet.forEach(education -> {educationDTOSet.add(new EducationDTO(education));});
        return educationDTOSet;
    }
}