package edu.demian.zinon.dto.auth.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
