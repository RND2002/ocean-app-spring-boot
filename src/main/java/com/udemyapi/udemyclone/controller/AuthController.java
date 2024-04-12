package com.udemyapi.udemyclone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication Controller",description = "Only to check if user is authenticated")
@RequestMapping("api/v1/auth")
public class AuthController {
    private static final Logger logger =LoggerFactory.getLogger(AuthController.class);
    @Operation(summary = "Basic Authentication Endpoint", description = "This endpoint does basic authentication.")
    @GetMapping("/basicauth")
    public String authController(){

        try{
            logger.info("Received a request for authentication");
            return "successfully authenticated";
        }catch (Exception e){
            e.printStackTrace();
            return "Authentication unsuccessfull";
        }
    }
}
