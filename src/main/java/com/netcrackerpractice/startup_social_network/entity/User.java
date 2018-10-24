package com.netcrackerpractice.startup_social_network.entity;

import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;
    private String password;
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
}




