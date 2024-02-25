package com.udemyapi.udemyclone.service;

import com.udemyapi.udemyclone.entity.Author;
import com.udemyapi.udemyclone.entity.Role;
import com.udemyapi.udemyclone.entity.User;
import com.udemyapi.udemyclone.entity.UserMapper;
import com.udemyapi.udemyclone.repository.AuthorRepository;
import com.udemyapi.udemyclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final AuthorRepository authorRepository;

    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<UserMapper> RegisterUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
         Collection<? extends GrantedAuthority> role= user.getAuthorities();
         if(role.contains("ROLE_AUTHOR")){
             Author author=new Author();
             author.setFirstName(user.getFirstname());
             author.setLastName(user.getLastname());
             author.setEmail(user.getEmail());
             authorRepository.save(author);
         }
         userRepository.save(user);

         return new ResponseEntity<>(userMapperDto(user), HttpStatus.OK);
    }

    public ResponseEntity<List<UserMapper>> getAlUsers() {

        List<User> users=userRepository.findAll();
        List<UserMapper> userMapperList=new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userMapperList.add(userMapperDto(users.get(i)));
        }
        return new ResponseEntity<>(userMapperList,HttpStatus.OK);
    }

    public UserMapper userMapperDto(User user){
        return new UserMapper(
                user.getFirstname(),
                user.getLastname(),
                user.getRoles()
        );
    }
}
