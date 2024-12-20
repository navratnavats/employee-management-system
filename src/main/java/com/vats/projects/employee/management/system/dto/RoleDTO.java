package com.vats.projects.employee.management.system.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private Integer id;
    private String name; // e.g., ADMIN, MANAGER, EMPLOYEE
}
