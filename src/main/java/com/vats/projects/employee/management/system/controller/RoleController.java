package com.vats.projects.employee.management.system.controller;

import com.vats.projects.employee.management.system.dto.RoleDTO;
import com.vats.projects.employee.management.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{name}")
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable String name) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(roleService.getRoleByName(name).getName());
        return ResponseEntity.ok(roleDTO);
    }
}
