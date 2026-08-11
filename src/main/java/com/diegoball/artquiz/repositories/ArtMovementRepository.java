package com.diegoball.artquiz.repositories;

import com.diegoball.artquiz.entities.ArtMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtMovementRepository extends JpaRepository<ArtMovement, Long> {
}
