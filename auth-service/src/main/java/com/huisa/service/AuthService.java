package com.huisa.service;

import com.huisa.dtos.auth.AuthRequest;
import com.huisa.dtos.auth.AuthResponse;
import com.huisa.dtos.request.UserRequest;
import com.huisa.dtos.response.UserResponse;

public interface AuthService {

    AuthResponse login (AuthRequest authRequest);
    UserResponse register (UserRequest userRequest);
    UserResponse registerAdmin(UserRequest userRequest);
}
