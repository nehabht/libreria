package com.libreria.controllers;

import com.libreria.dtos.AuthenticationRequest;
import com.libreria.dtos.AuthenticationResponse;
import com.libreria.dtos.SignupRequest;
import com.libreria.dtos.UserDto;
import com.libreria.services.auth.AuthService;
import com.libreria.services.auth.jwt.UserDetailsServiceImpl;
import com.libreria.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    //registration
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

    /**
     * Endpoint for user authentication, generates a JWT token upon successful login.
     * @param authenticationRequest User's login credentials (email and password).
     * @param response HTTP response to handle authentication errors.
     * @return AuthenticationResponse containing the generated JWT token.
     * @throws IOException If an error occurs while handling the HTTP response.
     */
    @PostMapping("/login")
    public AuthenticationResponse createAutheticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
            return null;
        }

        final UserDetails userDetails= userDetailsService.loadUserByUsername((authenticationRequest.getEmail()));
        final String jwt = jwtUtil.generateTokem((userDetails.getUsername()));
        return new AuthenticationResponse(jwt);
    }


}
