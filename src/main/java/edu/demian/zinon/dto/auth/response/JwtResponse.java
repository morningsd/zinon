package edu.demian.zinon.dto.auth.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {

    private Long id;
    private String token;
    private final String type = "Bearer";
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(Long id, String token, String username, String email, List<String> roles) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
