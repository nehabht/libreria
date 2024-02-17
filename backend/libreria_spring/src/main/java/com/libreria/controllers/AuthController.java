package com.libreria.controllers;

import com.libreria.dtos.SignupRequest;
import com.libreria.dtos.UserDto;
import com.libreria.services.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //login

    @PostMapping("signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {

        // Call the authService to create a new user based on the provided SignupRequest
        UserDto createdUserDto = authService.createUser(signupRequest);

        if (createdUserDto == null){
            return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
        }
    }
}
