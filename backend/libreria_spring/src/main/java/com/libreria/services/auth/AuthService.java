package com.libreria.services.auth;

import com.libreria.dtos.SignupRequest;
import com.libreria.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
