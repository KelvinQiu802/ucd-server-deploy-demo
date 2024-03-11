package com.example.springsecurejwtv2.controller;

import com.example.springsecurejwtv2.model.UserDTO;
import com.example.springsecurejwtv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/home")
    public String home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello " + authentication.getName();
    }

    @GetMapping("/users")
    public List<UserDTO> allUsers() {
        return userService.getAllUsers();
    }

}
