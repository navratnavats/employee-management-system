package com.vats.projects.employee.management.system.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String name; // e.g., ROLE_ADMIN, ROLE_MANAGER, ROLE_EMPLOYEE
}
