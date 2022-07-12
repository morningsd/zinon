package edu.demian.zinon.controller;

import edu.demian.zinon.controller.dto.RegisterUserDto;
import edu.demian.zinon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public String register(@RequestBody RegisterUserDto userDto) {
        boolean userExists = userService.findByEmail(userDto.getEmail()).isPresent();
        if (userExists) {
            throw new RuntimeException("User with such email is already exists");
        }

        return "registered";
    }

}
