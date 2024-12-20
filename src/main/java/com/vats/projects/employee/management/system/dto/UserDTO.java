package com.vats.projects.employee.management.system.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private Set<RoleDTO> roles;
}
