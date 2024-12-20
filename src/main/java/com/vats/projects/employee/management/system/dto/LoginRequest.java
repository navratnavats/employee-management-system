package com.vats.projects.employee.management.system.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
