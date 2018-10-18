package com.netcrackerpractice.startup_social_network.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Business_roles")
public class Business_role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "businessRole")
    private List<Resume> resumes;

    @OneToMany(mappedBy = "businessRole")
    private List<Skill> skills;

}
