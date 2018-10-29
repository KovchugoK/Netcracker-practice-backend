package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
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
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private Set<Resume> resumes;

    @OneToOne(mappedBy = "yourAccount", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "yourAccount", allowSetters = true)
    private Contact yourContact;


    @OneToMany(mappedBy = "otherAccount", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "otherAccount", allowSetters = true)
    private Set<Contact> otherContact;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private Set<Startup> startups;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private Set<StartupRole> startupRoles;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private Set<Favorite> favorites;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(Set<Resume> resumes) {
        this.resumes = resumes;
    }

    public Contact getYourContact() {
        return yourContact;
    }

    public void setYourContact(Contact yourContact) {
        this.yourContact = yourContact;
    }

    public Set<Contact> getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(Set<Contact> otherContact) {
        this.otherContact = otherContact;
    }

    public Set<Startup> getStartups() {
        return startups;
    }

    public void setStartups(Set<Startup> startups) {
        this.startups = startups;
    }

    public Set<StartupRole> getStartupRoles() {
        return startupRoles;
    }

    public void setStartupRoles(Set<StartupRole> startupRoles) {
        this.startupRoles = startupRoles;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

}
