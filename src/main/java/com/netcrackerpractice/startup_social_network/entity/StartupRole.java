package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Startups_Roles")

    public class StartupRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

        private boolean permission;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_startup")
        @JsonIgnoreProperties(value = "startupRoles", allowSetters = true)
        private Startup startup;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_account")
        @JsonIgnoreProperties(value = "startupRoles", allowSetters = true)
        private Account account;

    }
