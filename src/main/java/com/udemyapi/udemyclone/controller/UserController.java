package com.udemyapi.udemyclone.controller;

import com.udemyapi.udemyclone.entity.AuthorRequestDto;
import com.udemyapi.udemyclone.entity.User;
import com.udemyapi.udemyclone.entity.UserMapper;
import com.udemyapi.udemyclone.entity.UserRequestDto;
import com.udemyapi.udemyclone.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDto user){
        return userService.registerUser(user);
    }

    @GetMapping("get/{email}")
    public ResponseEntity<UserMapper> getUserByEmail(@PathVariable String email){
        return userService.findUserByEmail(email);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserMapper>> getAllUsers(){
        return userService.getAlUsers();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }

    @PutMapping("update/{email}")
    public ResponseEntity<String> makeUserAuthor(@PathVariable String email, @RequestBody AuthorRequestDto authorRequestDto){
        return userService.createUserAuthor(email,authorRequestDto);
    }
}
