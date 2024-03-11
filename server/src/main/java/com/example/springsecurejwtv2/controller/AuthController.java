package com.example.springsecurejwtv2.controller;

import com.example.springsecurejwtv2.auth.JwtUtils;
import com.example.springsecurejwtv2.model.AuthRequest;
import com.example.springsecurejwtv2.model.AuthResponse;
import com.example.springsecurejwtv2.model.RegisterRequest;
import com.example.springsecurejwtv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest authRequest
    ) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUserName(),
                        authRequest.getPassword()
                )
        );
        String jwt = jwtUtils.createToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(userDetails.getUsername(), jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        userService.createNewUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
