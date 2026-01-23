package com.huisa.controller;

import com.huisa.dtos.request.RoleRequest;
import com.huisa.service.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getRoleById( @PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleRequest roleRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleRequest));
    }


    @PutMapping
    public ResponseEntity<?> updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(roleService.updateRole(id, roleRequest));
    }


    @DeleteMapping
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }







}
