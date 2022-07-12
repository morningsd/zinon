package edu.demian.zinon.controller;

import edu.demian.zinon.dto.auth.LoginRequestDto;
import edu.demian.zinon.dto.auth.LoginResponseDto;
import edu.demian.zinon.dto.auth.RegisterUserDto;
import edu.demian.zinon.entity.Role;
import edu.demian.zinon.entity.Status;
import edu.demian.zinon.entity.User;
import edu.demian.zinon.security.jwt.JwtTokenProvider;
import edu.demian.zinon.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto) {
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = tokenProvider.createToken(email, user.getRole().name());
            return new ResponseEntity<>(new LoginResponseDto(email, token), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.FORBIDDEN);
        }
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return ResponseEntity.ok().build();
    }

}
