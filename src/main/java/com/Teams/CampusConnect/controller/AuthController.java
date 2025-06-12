package com.Teams.CampusConnect.controller;

import com.Teams.CampusConnect.DTOs.AuthRequest;
import com.Teams.CampusConnect.DTOs.UserRegisterDTO;
import com.Teams.CampusConnect.config.JwtUtil;

import com.Teams.CampusConnect.model.Role;
import com.Teams.CampusConnect.model.User;
import com.Teams.CampusConnect.repository.UserRepository;
import com.Teams.CampusConnect.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest auth) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));

            UserDetails user = userDetailsService.loadUserByUsername(auth.getUsername());
            String token = jwtUtil.generateToken(user);

            return ResponseEntity.ok(Collections.singletonMap("token", token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto) {
        Optional<User> existingUser = userRepository.findByUsername(dto.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.valueOf(dto.getRole().toUpperCase()));


        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
