package com.diegoball.artquiz.services;

import com.diegoball.artquiz.dto.artMovement.ArtMovementCreateDTO;
import com.diegoball.artquiz.entities.ArtMovement;
import com.diegoball.artquiz.repositories.ArtMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtMovementService {

    private final ArtMovementRepository artMovementRepository;


    public List<ArtMovement> findAll() {
        return artMovementRepository.findAll();
    }

    public ArtMovement findById(Long id) {
        return artMovementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Art movement not found with id: " + id));
    }

    public ArtMovement save(ArtMovementCreateDTO dto) {
        ArtMovement artMovement = new ArtMovement();
        artMovement.setName(dto.getName());
        artMovement.setCenturyEnd(dto.getCenturyEnd());
        artMovement.setCenturyStart(dto.getCenturyStart());
        artMovement.setLocation(dto.getLocation());
        artMovement.setMovementDescription(dto.getMovementDescription());
        return artMovementRepository.save(artMovement);
    }

    public void deleteById(Long id) {
        artMovementRepository.deleteById(id);
    }
}
