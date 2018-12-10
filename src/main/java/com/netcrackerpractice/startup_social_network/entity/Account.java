package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.netcrackerpractice.startup_social_network.view.View;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @JsonView(View.BasicInfo.class)
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JsonView(View.BasicInfo.class)
    @Column(name = "first_name")
    private String firstName;

    @JsonView(View.BasicInfo.class)
    @Column(name = "last_name")
    private String lastName;

    @JsonView(View.BasicInfo.class)
    private Date birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private User user;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "id_image")
    private String imageId;

    @Column(name = "id_compressed_image")
    private String compressedImageId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<Resume> resumes;

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    @OneToMany(mappedBy = "yourAccount", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "yourAccount", allowSetters = true)
    private List<Contact> yourContact;

    @OneToMany(mappedBy = "otherAccount", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "otherAccount", allowSetters = true)
    private List<Contact> otherContact;

    @OneToMany(mappedBy = "yourAccount")
    @JsonIgnoreProperties(value = "yourAccount", allowSetters = true)
    private List<Conversation> yourConversations;

    @OneToMany(mappedBy = "otherAccount")
    @JsonIgnoreProperties(value = "otherAccount", allowSetters = true)
    private List<Conversation> otherConversations;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<Startup> startups;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<StartupRole> startupRoles;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private Set<Education> educations;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<WorkExperience> workExperiences;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getCompressedImageId() {
        return compressedImageId;
    }

    public void setCompressedImageId(String compressedImageId) {
        this.compressedImageId = compressedImageId;
    }
}
