package com.huisa.service;

import com.huisa.dtos.request.UserRoleRequest;
import com.huisa.dtos.response.UserRoleResponse;

import java.util.List;

public interface UserRoleService {

    List<UserRoleResponse> getAllUserRoles();

    UserRoleResponse assignRoleToUser(UserRoleRequest userRoleRequest);

    void  removeRoleFromUser(Long userId, Long roleId);

    List<UserRoleResponse> getUserRolesByUserId(Long userId);

}
