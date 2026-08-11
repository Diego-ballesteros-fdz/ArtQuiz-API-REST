package com.diegoball.artquiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "art_movement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name= "century_start")
    private Integer centuryStart;

    @Column(name= "century_end")
    private Integer centuryEnd;

    private String location;

    @Column(name= "movement_description",columnDefinition = "TEXT")
    private String movementDescription;
}
