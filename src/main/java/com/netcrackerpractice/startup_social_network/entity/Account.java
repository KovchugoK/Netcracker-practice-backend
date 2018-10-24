package com.netcrackerpractice.startup_social_network.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private Set<AccountResumeBusinessRole> accountResumeBusinessRoles;

    @OneToOne(mappedBy = "yourAccount", cascade = CascadeType.ALL)
    private Contact yourContact;

    @OneToMany(mappedBy = "otherAccount", cascade = CascadeType.ALL)
    private Set<Contact> otherContact;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Startup> startups;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<StartupRole> startupRoles;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Favorite> favorites;


}
