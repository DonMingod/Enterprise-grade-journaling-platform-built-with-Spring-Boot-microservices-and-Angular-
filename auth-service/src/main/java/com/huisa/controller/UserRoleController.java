package com.huisa.controller;

import com.huisa.dtos.request.UserRoleRequest;
import com.huisa.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-roles")
public class UserRoleController {

    private  final UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<?> getAllUserRoles() {
        return ResponseEntity.ok(userRoleService.getAllUserRoles());
    }

    @GetMapping("/user/{userId}")
   public ResponseEntity<?> getUserRolesByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(userRoleService.getUserRolesByUserId(userId));
   }
    @PostMapping
    public ResponseEntity<?> assignRoleToUser(@Valid @RequestBody UserRoleRequest userRoleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRoleService.assignRoleToUser(userRoleRequest));
    }

    @DeleteMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        userRoleService.removeRoleFromUser(userId, roleId);
        return ResponseEntity.noContent().build();
    }

}
