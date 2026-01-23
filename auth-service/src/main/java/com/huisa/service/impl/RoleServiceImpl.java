package com.huisa.service.impl;

import com.huisa.dtos.request.RoleRequest;
import com.huisa.dtos.response.RoleResponse;
import com.huisa.model.Role;
import com.huisa.repository.RoleRepository;
import com.huisa.service.RoleService;
import com.huisa.util.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private  final RoleRepository roleRepository;

    private final RoleMapper roleMapper;


    @Override
    public List<RoleResponse> getAllRoles() {
        return roleMapper.toDtoList(roleRepository.findAll());
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return roleMapper.toDto(role);
    }

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toEntity(roleRequest);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole);
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest roleRequest) {
        Role roleFound = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleFound.setName(roleRequest.name());
        roleFound.setDescription(roleRequest.description());
        Role updatedRole = roleRepository.save(roleFound);
        return roleMapper.toDto(updatedRole);

    }

    @Override
    public void deleteRole(Long id) {
        Role roleFound = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        roleRepository.delete(roleFound);
    }
}
