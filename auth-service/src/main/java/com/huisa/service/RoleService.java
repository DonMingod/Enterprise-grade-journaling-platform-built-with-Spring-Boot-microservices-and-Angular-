package com.huisa.service;

import com.huisa.dtos.request.RoleRequest;
import com.huisa.dtos.response.RoleResponse;

import java.util.List;

public interface RoleService {

    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(Long id);

    RoleResponse createRole(RoleRequest roleRequest);

    RoleResponse updateRole(Long id, RoleRequest roleRequest);

    void deleteRole(Long id);

}
