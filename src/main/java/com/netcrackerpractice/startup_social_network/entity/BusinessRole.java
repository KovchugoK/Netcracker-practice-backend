package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcrackerpractice.startup_social_network.entity.enums.BusinessRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Business_roles")
public class BusinessRole {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    public BusinessRoleEnum getBusinessRoleName() {
        return businessRoleName;
    }

    public void setBusinessRoleName(BusinessRoleEnum businessRoleName) {
        this.businessRoleName = businessRoleName;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    @Column(name = "role_name")
    @Enumerated(value = EnumType.STRING)
    private BusinessRoleEnum businessRoleName;


    @OneToMany(mappedBy = "businessRole", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "businessRole", allowSetters = true)
    private List<Resume> resumes;

}
