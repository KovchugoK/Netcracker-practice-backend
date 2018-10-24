package com.netcrackerpractice.startup_social_network.entity;


import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
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
@Table(name = "Accounts_Resume_Business_role")
public class AccountResumeBusinessRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resume")
    private Resume resume;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    private Account account;

    @Enumerated(EnumType.STRING)
    private BusinessRoleEnum businessRole;
}
