package com.sms.demo.controller;

import com.sms.demo.entity.User;
import com.sms.demo.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private CustomUserDetailsService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setUsername("newuser");
        testUser.setPassword("password123");
        testUser.setRole(User.Role.STUDENT);
    }

    @Test
    void register_shouldRegisterUser_whenUsernameNotExists() {
        // Given
        when(userService.findByUsername("newuser")).thenReturn(null);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userService.save(any(User.class))).thenReturn(testUser);

        // When
        ResponseEntity<String> response = authController.register(testUser);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("User registered successfully", response.getBody());
        verify(userService, times(1)).findByUsername("newuser");
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void register_shouldReturnBadRequest_whenUsernameAlreadyExists() {
        // Given
        when(userService.findByUsername("newuser")).thenReturn(testUser);

        // When
        ResponseEntity<String> response = authController.register(testUser);

        // Then
        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        assertEquals("Username already exists", response.getBody());
        verify(userService, times(1)).findByUsername("newuser");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userService, never()).save(any(User.class));
    }
}
