package com.netcrackerpractice.startup_social_network.entity;

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
@Table(name = "Resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String info;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "id_business_role")
    private Business_role businessRole;


}
