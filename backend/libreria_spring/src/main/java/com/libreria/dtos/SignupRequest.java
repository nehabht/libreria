package com.libreria.dtos;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;


    private String lastname;


    private String email;


    private String password;
}
