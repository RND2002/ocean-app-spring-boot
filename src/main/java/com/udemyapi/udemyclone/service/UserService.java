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
//    public ResponseEntity<UserMapper> RegisterUser(UserRequestDto user){
//
//        User user1=new User();
//        Role role=new Role();
//        role.setName("ROLE_USER");
//        Set<Role> roles=new HashSet<>();
//        roles.add(role);
//        roleRepository.save(role);
////        role.setName(user.role().toString()); //Todo-here is a glitch make it correct in future
//        user1.setFirstname(user.firstName());
//        user1.setLastname(user.lastName());
//        user1.setEmail(user.email());
//        user1.setRoles(roles);
//        user1.setPassword(passwordEncoder.encode(user.password()));
//        user1.setCreatedAt(LocalDateTime.now());
//        System.out.println("Hello from register user");
//
//
//         try{
//             userRepository.save(user1);
//         }catch (Exception e){
//             return new ResponseEntity<>(userMapperDto(user1),HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//        return new ResponseEntity<>(userMapperDto(user1), HttpStatus.OK);
//
//    }
//public ResponseEntity<UserMapper> registerUser(UserRequestDto user) {
//    try {
//        User user1 = new User();
//        user1.setFirstname(user.firstName());
//        user1.setLastname(user.lastName());
//        user1.setEmail(user.email());
//        user1.setPassword(passwordEncoder.encode(user.password()));
//        user1.setCreatedAt(LocalDateTime.now());
//        //Role role=new Role();
//        roleRepository.save(user.role());
//        user1.setRoles(user.role());
//
//        // Save the role to the database
////        roleRepository.save(role);
//
//
////        Set<Role> roles = new HashSet<>();
////        roles.add(role);
////        user1.setRoles(roles);
//
//        // Save the user to the database
//        userRepository.save(user1);
//
//        // Return successful response with user details
//        return new ResponseEntity<>(userMapperDto(user1), HttpStatus.OK);
//    } catch (Exception e) {
//        // If an error occurs during saving, return an internal server error response
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
//public ResponseEntity<UserMapper> registerUser(UserRequestDto userDto) {
//    try {
//        // Create a new User entity
//        User user = new User();
//        user.setFirstname(userDto.firstName());
//        user.setLastname(userDto.lastName());
//        user.setEmail(userDto.email());
//        user.setPassword(passwordEncoder.encode(userDto.password()));
//        user.setCreatedAt(LocalDateTime.now());
//        Collection<? extends GrantedAuthority> role= (Collection<? extends GrantedAuthority>) userDto.role();
//        user.
//        // Save the User entity to the database
//        userRepository.save(user);
//
//        // Save associated roles to the database and associate them with the user
//        if (userDto.role() != null && !userDto.role().isEmpty()) {
//            // Iterate over roles in the UserRequestDto and save each role
//            for (Role role : userDto.role()) {
//                // Check if the role already exists in the database
//                Optional<Role> existingRole = roleRepository.findByName(role.getName());
//                if (existingRole.isPresent()) {
//                    user.getRoles().add(existingRole.get());
//                } else {
//                    // If the role doesn't exist, save it to the database
//                    Role savedRole = roleRepository.save(role);
//                    user.getRoles().add(savedRole);
//                }
//            }
//        }
//
//        // Save the updated user entity with associated roles
//        userRepository.save(user);
//
//        // Return successful response with user details
//        return new ResponseEntity<>(userMapperDto(user), HttpStatus.OK);
//    } catch (Exception e) {
//        // If an error occurs during saving, return an internal server error response
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}

    public ResponseEntity<String> registerUser(UserRequestDto userDto) {
        // Check if the user already exists
        if (userRepository.findByEmail(userDto.email()) != null) {
            throw new RuntimeException("User with this email already exists");
        }

        // Create user entity
        User user = new User();
        user.setFirstname(userDto.firstName());
        user.setLastname(userDto.lastName());
        user.setEmail(userDto.email());
        user.setPassword(passwordEncoder.encode(userDto.password())); // Encrypt password
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        System.out.println("Fuck");
        // Set roles for the user
        Set<Role> roles = new HashSet<>();
        List<Role> roleList=new ArrayList<>(userDto.role());
        for(int  i=0;i<roleList.size();i++){
            Role role = roleRepository.findByName(roleList.get(i).getName());
            System.out.println("Fuck Again");
            if (role == null) {
                // Role doesn't exist, create a new one
                 role = new Role();
               role.setName(roleList.get(i).getName());
                roleRepository.save(role); // Save the new role
            }
            roles.add(role);
        }
        user.setRoles(roles);

        // Save the user
        userRepository.save(user);
        return new ResponseEntity<>("User created",HttpStatus.OK);
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
                user.getEmail(),
                user.getRoles()
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

    public ResponseEntity<UserMapper> findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        UserMapper userMapper=userMapperDto(user);
        return new ResponseEntity<>(userMapper,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUser(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow();
        if(Objects.equals(user.getId(), userId)){
            userRepository.deleteById(userId);
        }

        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }

//    public ResponseEntity<String> createUserAuthor(String email, AuthorRequestDto authorRequestDto) {
//        User user=userRepository.findByEmail(email);
//        User user1=new User();
//        user1.setCreatedAt(user.getCreatedAt());
//        user1.setUpdatedAt(LocalDateTime.now());
//        user1.setFirstname(user.getFirstname());
//        user1.setLastname(user.getLastname());
//        user1.setPassword(user.getPassword());
//        user1.setEmail(user.getEmail());
//        user1.setCourses(user.getCourses());
//        user1.setId(user.getId());
//
//        List<Role> roles=new ArrayList<>(user.getRoles());
//        Role role=new Role();
//        role.setName(authorRequestDto.role());
//
//        roleRepository.save(role);
//        roles.add(role);
//        Set<Role> roleSet=new HashSet<>(roles);
//        user1.setRoles(roleSet);
//
//        userRepository.save(user1);
//        return new ResponseEntity<>("Successfully",HttpStatus.OK);
//    }
public ResponseEntity<String> createUserAuthor(String email, AuthorRequestDto authorRequestDto) {
    try {
        // Find the existing user by email
        User user = userRepository.findByEmail(email);

        // Create a new user based on the existing user's information
//        User user1 = new User();
//        user1.setCreatedAt(user.getCreatedAt());
//        user1.setUpdatedAt(LocalDateTime.now());
//        user1.setFirstname(user.getFirstname());
//        user1.setLastname(user.getLastname());
//        user1.setPassword(user.getPassword());
//        user1.setEmail(user.getEmail());
//        user1.setCourses(user.getCourses());
//        user1.setId(user.getId());

        // Create a new role for the author
        Role role = new Role();
        role.setName("ROLE_AUTHOR"); // Assuming author role is "ROLE_AUTHOR"

        // Save the new role
        roleRepository.save(role);
        user.getRoles().add(role);
        // Get the existing roles of the user and add the new role
        List<Role> roles = new ArrayList<>(user.getRoles());
        roles.add(role);

        // Set the roles for the new user
        Set<Role> roleSet = new HashSet<>(roles);
        user.setRoles(roleSet);

        // Save the new user with the updated roles
        userRepository.save(user);

        // Return success response
        return new ResponseEntity<>("Successfully created user as author", HttpStatus.OK);
    } catch (Exception e) {
        // Return error response if an exception occurs
        return new ResponseEntity<>("Failed to create user as author", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
