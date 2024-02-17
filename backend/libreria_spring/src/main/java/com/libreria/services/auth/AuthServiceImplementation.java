package com.libreria.services.auth;

import com.libreria.dtos.SignupRequest;
import com.libreria.dtos.UserDto;
import com.libreria.entities.User;
import com.libreria.enums.UserRole;
import com.libreria.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;


    public AuthServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    public UserDto createUser(SignupRequest signupRequest) {

        // Create a new User object
        User user = new User();
        user.setName(signupRequest.getName());
        user.setLastname(signupRequest.getLastname());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.USER);

        // Save the new user to the repository (database)
        User createdUser = userRepository.save(user);

        // Create a UserDto object to represent the newly created user
        UserDto createdUserDto = new UserDto();
        createdUserDto.setId(createdUser.getId());
        createdUserDto.setName(createdUser.getName());
        createdUserDto.setLastname(createdUser.getLastname());
        createdUserDto.setEmail(createdUser.getEmail());
        createdUserDto.setUserRole(createdUser.getUserRole());

        // Return the UserDto object representing the newly created user
        return createdUserDto;
    }
}
