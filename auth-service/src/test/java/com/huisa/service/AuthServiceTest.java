package com.huisa.service;

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
import com.huisa.service.impl.AuthServiceImpl;
import com.huisa.util.UserMapper;
import com.huisa.model.*;
import com.huisa.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_validRequest_returnsToken() {
        AuthRequest request = new AuthRequest("test@example.com", "1234");
        User user = User.builder().id(1L).username("testuser").email("test@example.com").enabled(true).build();
        UserRole userRole= UserRole.builder().role(Role.builder().name("ROLE_USER").build()).build();
        List<UserRole> userRoles = List.of(userRole);

        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(userRepository.findByEmailOrUsername(anyString())).thenReturn(Optional.of(user));
        when(userRoleRepository.findByUserId(user.getId())).thenReturn(userRoles);
     //   when(jwtUtil.generateToken(user.getEmail(), List.of("ROLE_USER"))).thenReturn("mocked-jwt-token");
      //  when(jwtUtil.generateToken(eq(user.getEmail()), anyList())).thenReturn("mocked-jwt-token");
     //   when(jwtUtil.generateToken(eq(user.getEmail()), anyList())).thenReturn("mocked-jwt-token");
        when(jwtUtil.generateToken(eq(user.getUsername()), anyList())).thenReturn("mocked-jwt-token");

        AuthResponse authResponse = authService.login(request);

        assertThat(authResponse).isNotNull();
        assertThat(authResponse.token()).isEqualTo("mocked-jwt-token");
        assertThat(authResponse.username()).isEqualTo("testuser");
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

    }

    @Test
  void  register_validRequest_savesUser() {
        UserRequest userRequest = new UserRequest(
                "test@example.com",
                "1234",
                "testuser",
                "Test",
                "User"              );
        User user = User.builder().id(1L).username("testuser").email("test@example.com").enabled(true).build();
        User mappedUser = User.builder().id(null).username("testuser").email("test@example.com") .build();
        Role roleUser = Role.builder().name("ROLE_USER").build();

        when(userMapper.toEntity(any(UserRequest.class))).thenReturn(mappedUser);
        when(passwordEncoder.encode(anyString())).thenReturn("hashed");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(roleUser));
        when(userRoleRepository.save(any(UserRole.class))).thenReturn(null);
        when(userMapper.toDto(user)).thenReturn(
        new UserResponse(1L, "test@example.com", "testuser"
                , "Test", "User"
                , true, LocalDateTime.now(), LocalDateTime.now())
        );

        UserResponse userResponse = authService.register(userRequest);

        assertThat(userResponse).isNotNull();
        assertThat(userResponse.username()).isEqualTo("testuser");
        assertThat(userResponse.id()).isEqualTo(1L);
        verify(passwordEncoder).encode("1234");
        verify(userRepository).save(any(User.class));
        verify(userRoleRepository).save(any());
    }

}
