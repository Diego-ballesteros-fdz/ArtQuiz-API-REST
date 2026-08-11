package com.diegoball.artquiz.repositories;

import com.diegoball.artquiz.entities.ArtWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtWorkRepository extends JpaRepository<ArtWork, Long> {
}
