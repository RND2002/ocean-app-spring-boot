package com.udemyapi.udemyclone.service;

import com.udemyapi.udemyclone.entity.*;
import com.udemyapi.udemyclone.repository.AuthorRepository;
import com.udemyapi.udemyclone.repository.RoleRepository;
import com.udemyapi.udemyclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final AuthorRepository authorRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private static final Object roleName="ROLE_AUTHOR";
    public ResponseEntity<UserMapper> RegisterUser(UserRequestDto user){

        User user1=new User();
        Role role=new Role();
        role.setName(user.role().toString()); //Todo-here is a glitch make it correct in future
        user1.setFirstname(user.firstName());
        user1.setLastname(user.lastName());
        user1.setEmail(user.email());
        user1.setRoles(user.role());
        user1.setPassword(passwordEncoder.encode(user.password()));
        user1.setCreatedAt(LocalDateTime.now());
        System.out.println("Hello from register user");
//        if(user1.getRoles().contains(roleName)){
//            System.out.println("Hello from if user");
//             Author author=new Author();
//             author.setFirstName(user1.getFirstname());
//             author.setLastName(user1.getLastname());
//             author.setEmail(user1.getEmail());
//             try{
//                 userRepository.save(user1);
//                 authorRepository.save(author);
//                 return new ResponseEntity<>(userMapperDto(user1),HttpStatus.OK);
//             }catch (Exception e){
//                 e.printStackTrace();
//                 user1.setFirstname("error");
//                 user1.setEmail("Error email");
//                 return new ResponseEntity<>(userMapperDto(user1),HttpStatus.INTERNAL_SERVER_ERROR);
//             }
//         }

         try{
             userRepository.save(user1);
         }catch (Exception e){
             return new ResponseEntity<>(userMapperDto(user1),HttpStatus.INTERNAL_SERVER_ERROR);
         }
        return new ResponseEntity<>(userMapperDto(user1), HttpStatus.OK);

    }

    public ResponseEntity<List<UserMapper>> getAlUsers() {
        List<User> users=userRepository.findAll();
        List<UserMapper> userMapperList=new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userMapperList.add(userMapperDto(users.get(i)));
        }
        return new ResponseEntity<>(userMapperList,HttpStatus.OK);
    }

    private UserMapper userMapperDto(User user){
        return new UserMapper(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail()
        );
    }

    private Set<String> listRoles(User user){
        List<Role> roles=new ArrayList<>(user.getRoles());
        Set<String> roleSet=new HashSet<>();
        for(int i=0;i<roles.size();i++){
            roleSet.add(roles.get(i).getName());
        }
        return roleSet;
    }
}
