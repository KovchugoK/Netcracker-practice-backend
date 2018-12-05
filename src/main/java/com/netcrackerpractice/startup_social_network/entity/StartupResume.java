package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Startups_resumes")
public class StartupResume {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resume")
    @JsonIgnoreProperties(value = "startupResumes", allowSetters = true)
    private Resume resume;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_startup")
    @JsonIgnoreProperties(value = "startupResumes", allowSetters = true)
    private Startup startup;
}
