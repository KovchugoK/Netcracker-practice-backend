package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "investments")
public class Investment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_investor")
    @JsonIgnoreProperties(value = {"startupInvestments", "birthday", "aboutMe", "imageId"
    , "compressedImageId", "nonBlock", "resumes", "yourContact", "otherContact", "yourConversations",
            "otherConversations", "startups", "favorites", "educations", "workExperiences", "user"}, allowSetters = true)
    private Account investor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_startup")
    @JsonIgnoreProperties(value = {"startupInvestments", "account", "startupResumes", "startupRoles", "startupName"
    ,"idea", "aboutProject", "businessPlan", "sumOfInvestment", "dateOfCreation", "imageId", "compressedImageId", "nonBlock"}, allowSetters = true)
    private Startup startup;

    @JoinColumn(name = "sum_of_investment")
    private int sumOfInvestment;
}
