package com.libreria.services.auth;

import com.libreria.dtos.SignupRequest;
import com.libreria.dtos.UserDto;
import com.libreria.entities.User;
import com.libreria.enums.UserRole;
import com.libreria.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;


    public AuthServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    /**
     * Method to create an admin account by querying the UserRepository
     * for an existing user with the role of ADMIN.
     */
    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount == null){
            User user = new User();
            user.setName("admin");
            user.setLastname("admin");
            user.setEmail("admin@gmail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
        }
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
