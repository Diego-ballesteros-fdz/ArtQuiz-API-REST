package com.diegoball.artquiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artworks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    private Integer year;

    @Column(name = "image_url")
    private String imageUrl;

    private String museum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_movement_id")
    private ArtMovement artMovement;

}
