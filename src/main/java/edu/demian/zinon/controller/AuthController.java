package edu.demian.zinon.controller;

import edu.demian.zinon.controller.dto.RegisterUserDto;
import edu.demian.zinon.entity.Role;
import edu.demian.zinon.entity.Status;
import edu.demian.zinon.entity.User;
import edu.demian.zinon.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/register")
    public String register(@RequestBody RegisterUserDto userDto) {
        boolean userExists = userService.findByEmail(userDto.getEmail()).isPresent();
        if (userExists) {
            throw new RuntimeException("User with such email is already exists");
        }

        User userToSave = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();

        User savedUser = userService.save(userToSave);
        return savedUser.getId().toString();
    }

}
