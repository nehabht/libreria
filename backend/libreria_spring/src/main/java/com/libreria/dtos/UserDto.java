package com.libreria.dtos;

import com.libreria.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;


    private String name;


    private String lastname;


    private String email;


    private String password;

    private UserRole userRole;


}
