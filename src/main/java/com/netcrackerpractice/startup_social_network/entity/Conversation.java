package com.netcrackerpractice.startup_social_network.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_your_account", nullable = false)
    @JsonBackReference
    private Account firstAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_other_account", nullable = false)
    @JsonBackReference
    private Account secondAccount;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Message> messages;
}
