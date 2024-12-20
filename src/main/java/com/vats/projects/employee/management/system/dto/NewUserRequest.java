package com.vats.projects.employee.management.system.dto;

import lombok.Data;

import java.util.Set;

@Data
public class NewUserRequest {
    private String username;
    private String password;
    private Set<RoleDTO> roleDTO;
}
