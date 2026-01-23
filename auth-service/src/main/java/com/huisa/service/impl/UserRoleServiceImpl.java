package com.huisa.service.impl;

import com.huisa.dtos.request.UserRoleRequest;
import com.huisa.dtos.response.UserRoleResponse;
import com.huisa.model.UserRole;
import com.huisa.model.UserRoleId;
import com.huisa.repository.UserRoleRepository;
import com.huisa.service.UserRoleService;
import com.huisa.util.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl  implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRoleMapper userRoleMapper;


    @Override
    public List<UserRoleResponse> getAllUserRoles() {
        return userRoleMapper.toDtoList(userRoleRepository.findAll());
    }

    @Override
    public UserRoleResponse assignRoleToUser(UserRoleRequest userRoleRequest) {
        UserRole userRole = userRoleMapper.toEntity(userRoleRequest);
        UserRole savedUserRole = userRoleRepository.save(userRole);
        return userRoleMapper.toDto(savedUserRole);

    }

    @Override
    public void removeRoleFromUser(Long userId, Long roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found with userId: " + userId + " and roleId: " + roleId));
        userRoleRepository.delete(userRole);
    }

    @Override
    public List<UserRoleResponse> getUserRolesByUserId(Long userId) {
        return userRoleMapper.toDtoList(userRoleRepository.findByUserId(userId));
    }
}
