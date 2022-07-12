package edu.demian.zinon.controller.dto;

import lombok.Data;

@Data
public class RegisterUserDto {

    private final String email;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;

}
