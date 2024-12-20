package com.vats.projects.employee.management.system.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password; // For internal use; avoid sending to clients
    private Set<RoleDTO> roles;
}
