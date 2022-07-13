package edu.demian.zinon.dto.auth.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private final String email;

    @NotBlank
    @Size(min = 3, max = 20)
    private final String username;

    @NotBlank
    @Size(max = 50)
    private final String firstName;

    @NotBlank
    @Size(max = 50)
    private final String lastName;

    @NotBlank
    //TODO add min
    @Size(max = 40)
    private final String password;

}
