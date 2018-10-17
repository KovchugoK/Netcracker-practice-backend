package com.netcrackerpractice.startup_social_network.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private Date birthday;

    @OneToMany(mappedBy = "account")
    private List<Resume> resumes;

    public Account() {
    }

    public long getId() {
        return id;
    }

    public Account(String firstName, String secondName, Date birthday, List<Resume> resumes) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.resumes = resumes;
    }
}
