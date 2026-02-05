package com.sms.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.demo.entity.User;
import com.sms.demo.service.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomUserDetailsService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomUserDetailsService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Check if user already exists
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login successful! Use Basic Auth with your username and password.");
    }
}
