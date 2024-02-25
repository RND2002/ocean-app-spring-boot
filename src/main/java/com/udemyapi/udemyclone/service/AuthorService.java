package com.udemyapi.udemyclone.service;

import com.udemyapi.udemyclone.entity.Author;
import com.udemyapi.udemyclone.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    public ResponseEntity<Author> updateAuthorInfo(Integer id,Author author){
        Author updatedAuthor=authorRepository.findById(id).orElseThrow();
        updatedAuthor.setEmail(author.getEmail());
        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setLastName(author.getLastName());
        updatedAuthor.setUpdatedAt(LocalDateTime.now());
        updatedAuthor.setAge(author.getAge());
        authorRepository.save(updatedAuthor);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAuthor(Integer id){
        Author author =authorRepository.findById(id).orElseThrow();
        authorRepository.delete(author);
        return new ResponseEntity<>("Author deleted successfully",HttpStatus.OK);
    }
}
