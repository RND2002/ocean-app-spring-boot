package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.Author;

import com.udemyapi.udemyclone.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PutMapping("/update")
    public ResponseEntity<Author> updateAuthorInfo(@PathVariable Integer id,@RequestBody Author author){
        return authorService.updateAuthorInfo(id,author);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id){
        return authorService.deleteAuthor(id);
    }

}
