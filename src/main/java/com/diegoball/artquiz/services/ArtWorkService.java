package com.diegoball.artquiz.services;

import com.diegoball.artquiz.dto.artWork.ArtWorkCreateDTO;
import com.diegoball.artquiz.entities.ArtMovement;
import com.diegoball.artquiz.entities.ArtWork;
import com.diegoball.artquiz.entities.Author;
import com.diegoball.artquiz.repositories.ArtMovementRepository;
import com.diegoball.artquiz.repositories.ArtWorkRepository;
import com.diegoball.artquiz.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtWorkService {

    private final ArtWorkRepository artWorkRepository;
    private final AuthorRepository authorRepository;
    private final ArtMovementRepository artMovementRepository;

    public List<ArtWork> findAll() {
        return artWorkRepository.findAll();
    }

    public ArtWork findById(Long id) {
        return artWorkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Art Work not found with id: " + id));
    }

    public ArtWork save(ArtWorkCreateDTO dto) {
        ArtWork artWork = new ArtWork();
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        ArtMovement artMovement = artMovementRepository.findById(dto.getArtMovementId())
                .orElseThrow(() -> new RuntimeException("ArtMovement not found"));
        artWork.setAuthor(author);
        artWork.setArtMovement(artMovement);
        artWork.setTitle(dto.getTitle());
        artWork.setMuseum(dto.getMuseum());
        artWork.setYear(dto.getYear());
        artWork.setImageUrl(dto.getImageUrl());

        return artWorkRepository.save(artWork);
    }

    public void deleteById(Long id) {
        artWorkRepository.deleteById(id);
    }
}
