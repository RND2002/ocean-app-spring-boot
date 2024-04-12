package com.udemyapi.udemyclone.controller;

import com.sun.jdi.request.InvalidRequestStateException;
import com.udemyapi.udemyclone.entity.AuthorRequestDto;
import com.udemyapi.udemyclone.entity.User;
import com.udemyapi.udemyclone.entity.UserMapper;
import com.udemyapi.udemyclone.entity.UserRequestDto;
import com.udemyapi.udemyclone.exception.InvalidRequestException;
import com.udemyapi.udemyclone.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User Controller",description = "APIs perform crud operation on user")
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    @PostMapping("/register")
    @Operation(summary = " For registration of user as ROLE_USER")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDto user){
       try{
           logger.info("Received a request for registration of User with default role of USER");
           return userService.registerUser(user);
       }catch (InvalidRequestException invalidRequestException){
           logger.error("Invalid UserRequest DTO received: {}", invalidRequestException.getMessage());
//           logger.debug(invalidRequestException.getMessage());
            return new ResponseEntity<>(invalidRequestException.getMessage(), HttpStatus.BAD_REQUEST);
       }catch (Exception e){
           logger.error("Internal Server error occurred");
           e.printStackTrace();
           return new ResponseEntity<>("Internal Error Occurred",HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("get/{email}")
    @Operation(summary = "For retrieving user by the email-id")
    public ResponseEntity<UserMapper> getUserByEmail(@PathVariable String email){
       try{
           logger.info("Received a request to retrieve user by email-id[{}]",email);
           return userService.findUserByEmail(email);
       }catch (InvalidRequestException invalidRequestException){
           logger.error("Invalid email-id:{}",invalidRequestException.getMessage());
            UserMapper userMapper= UserMapper.withErrorMessage(invalidRequestException.getMessage());
            return new ResponseEntity<>(userMapper,HttpStatus.BAD_REQUEST);
       }catch (Exception e){
           logger.error("Internal Server Occurred");
           e.printStackTrace();
           UserMapper userMapper=UserMapper.withErrorMessage(e.getMessage());
           return new ResponseEntity<>(userMapper,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

//    @GetMapping("/all")
//
//    public ResponseEntity<List<UserMapper>> getAllUsers(){
//        return userService.getAlUsers();
//    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "For deleting user by id of user")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
        try{
            logger.info("Request to delete user by id of user:LoggedInUser id[{}]",userId);
            return userService.deleteUser(userId);
        }catch(InvalidRequestException invalidRequestException){
            logger.error("Invalid User Id received [{}]",invalidRequestException.getMessage());
            return new ResponseEntity<>("Invalid User id or user id does not exist",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Internal Server error occured[{}]",e.getMessage());
            return new ResponseEntity<>("Internal Server error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{email}")
    @Operation(summary = "For update in user Data")
    public ResponseEntity<String> makeUserAuthor(@PathVariable String email, @RequestBody AuthorRequestDto authorRequestDto){
        try{
            logger.info("Request received for update in user with email [{}] and author request dto [{}]"
                    ,email,authorRequestDto);
            return userService.createUserAuthor(email,authorRequestDto);
        }catch (InvalidRequestException e){
            logger.error("Invalid user id or invalid author Request [{}]",e.getMessage());
            return new ResponseEntity<>("Invalid Request error",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error("Internal Server error occurred");
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
