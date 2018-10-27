package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id_your_account")
    @JsonIgnoreProperties(value = "yourContact", allowSetters = true)
    private Account yourAccount;


    @ManyToOne()
    @JoinColumn(name = "id_contact_account")
    @JsonIgnoreProperties(value = "otherContact", allowSetters = true)
    private Account otherAccount;
}
