package com.huisa.service.impl;

import com.huisa.dtos.auth.AuthRequest;
import com.huisa.dtos.auth.AuthResponse;
import com.huisa.dtos.request.UserRequest;
import com.huisa.dtos.response.UserResponse;
import com.huisa.jwtconfig.JwtUtil;
import com.huisa.model.Role;
import com.huisa.model.User;
import com.huisa.model.UserRole;
import com.huisa.repository.RoleRepository;
import com.huisa.repository.UserRepository;
import com.huisa.repository.UserRoleRepository;
import com.huisa.service.AuthService;
import com.huisa.util.UserMapper;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.emailOrUsername(),
                        authRequest.password()
                )
        );
        User user = userRepository.findByEmailOrUsername(authRequest.emailOrUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        List<String> roles = userRoles.stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList());

        String token = jwtUtil.generateToken(user.getUsername(), roles);

        return AuthResponse.builder()
                .id(user.getId())
                .token(token)
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles)
                .build();
    }


    @Override
    public UserResponse register(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));
         UserRole userRoleEntity = UserRole.builder()
                .user(savedUser)
                .role(userRole)
                .build();
        userRoleRepository.save(userRoleEntity);
        return userMapper.toDto(savedUser);
    }
}
