package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcrackerpractice.startup_social_network.entity.enums.RoleEnum;
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
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "role", allowSetters = true)
    private List<User> users;
}
