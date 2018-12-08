package com.netcrackerpractice.startup_social_network.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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
    private Account yourAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_other_account", nullable = false)
    private Account otherAccount;

    private String name;

    @OneToMany(mappedBy = "conversation")
    List<Message> messages;
}
