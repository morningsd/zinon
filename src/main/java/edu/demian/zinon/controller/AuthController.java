package edu.demian.zinon.controller;

import edu.demian.zinon.dto.auth.request.LoginRequest;
import edu.demian.zinon.dto.auth.response.JwtResponse;
import edu.demian.zinon.dto.auth.request.RegisterRequest;
import edu.demian.zinon.dto.auth.response.MessageResponse;
import edu.demian.zinon.entity.Role;
import edu.demian.zinon.entity.Status;
import edu.demian.zinon.entity.User;
import edu.demian.zinon.security.CustomUserDetailsImpl;
import edu.demian.zinon.security.jwt.JwtTokenProvider;
import edu.demian.zinon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already in use!"));
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ROLE_USER)
                .status(Status.ACTIVE)
                .build();

        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(new JwtResponse(userDetails.getId(), jwt, userDetails.getUsername(), userDetails.getEmail(), roles));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return ResponseEntity.ok().build();
    }

}
