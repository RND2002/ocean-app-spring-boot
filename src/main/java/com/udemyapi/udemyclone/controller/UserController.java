package com.udemyapi.udemyclone.controller;

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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserMapper> registerUser(@Valid @RequestBody UserRequestDto user){
        return userService.RegisterUser(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserMapper>> getAllUsers(){
        return userService.getAlUsers();
    }
}
