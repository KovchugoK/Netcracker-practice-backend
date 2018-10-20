package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private Date birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<AccountResumeBusinessRole> accountResumeBusinessRoles;

    @OneToOne(mappedBy = "yourAccount", cascade = CascadeType.ALL)
    private Contact yourContact;

    @OneToMany(mappedBy = "otherAccount", cascade = CascadeType.ALL)
    private List<Contact> otherContact;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Startup> startups;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<StartuprRole> startuprRoles;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Favorite> favorites;


}
