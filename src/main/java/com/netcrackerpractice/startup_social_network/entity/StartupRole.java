package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Startups_Roles")
public class StartupRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean permission;

    @ManyToOne
    @JoinColumn(name = "id_startup")
    @JsonIgnoreProperties(value = "startupRoles", allowSetters = true)
    private Startup startup;

    @ManyToOne
    @JoinColumn(name = "id_account")
    @JsonIgnoreProperties(value = "startupRoles", allowSetters = true)
    private Account account;

}
