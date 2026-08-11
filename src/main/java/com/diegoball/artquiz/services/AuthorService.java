package com.diegoball.artquiz.services;

import com.diegoball.artquiz.dto.author.AuthorCreateDTO;
import com.diegoball.artquiz.dto.author.AuthorUpdateDTO;
import com.diegoball.artquiz.entities.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.diegoball.artquiz.repositories.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    public Author save(AuthorCreateDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBirthYear(dto.getBirthYear());
        author.setDeathYear(dto.getDeathYear());
        author.setNationality(dto.getNationality());
        author.setBiography(dto.getBiography());
        return authorRepository.save(author);
    }

    public Author partialUpdate(Long id, AuthorUpdateDTO dto) {
        //encontramos el autor
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        //modificamos el autor
        if (dto.getName() != null) {
            author.setName(dto.getName());
        }
        if (dto.getBirthYear() != null) {
            author.setBirthYear(dto.getBirthYear());
        }
        if (dto.getDeathYear() != null) {
            author.setDeathYear(dto.getDeathYear());
        }
        if (dto.getNationality() != null) {
            author.setNationality(dto.getNationality());
        }
        if (dto.getBiography() != null) {
            author.setBiography(dto.getBiography());
        }
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
