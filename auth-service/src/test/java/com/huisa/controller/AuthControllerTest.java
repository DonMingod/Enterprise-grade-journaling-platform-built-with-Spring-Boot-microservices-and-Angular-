package com.huisa.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.huisa.dtos.auth.AuthRequest;
import com.huisa.dtos.auth.AuthResponse;
import com.huisa.dtos.request.UserRequest;
import com.huisa.dtos.response.UserResponse;
import com.huisa.jwtconfig.JwtUtil;
import com.huisa.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtUtil jwtUtil;
    @MockitoBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void login_returnsJwtToken() throws Exception {
        AuthRequest loginRequest = new AuthRequest("test@example.com", "123");
        AuthResponse loginResponse = new AuthResponse(1L, "jwt-token",
                "testuser", "test@example.com", List.of("ROLE_USER"));
        when(authService.login(any(AuthRequest.class))).thenReturn(loginResponse);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void register_returnsJwtToken() throws Exception {
        UserRequest userRequest = new UserRequest("test@example.com", "testuser",
                "123", "Test", "User");
        UserResponse userResponse = new UserResponse(55L, "test@example.com",
                "testuser", "Test", "User", true,
                LocalDateTime.now(), LocalDateTime.now());

        when(authService.register(any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.id").value(55));
    }
}
