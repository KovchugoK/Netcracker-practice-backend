package com.netcrackerpractice.startup_social_network.entity;

import com.netcrackerpractice.startup_social_network.annotations.IgnoreDuringScan;
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
@Table(name = "Educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "institution")
    private String institution;

    @Column(name = "completion_year")
    private int completionYear;

    @ManyToOne
    @JoinColumn(name = "id_resume")
    private Resume resume;
}
