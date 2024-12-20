package com.vats.projects.employee.management.system.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String designation;
    private String bloodGroup;
    private Integer salary;
    private Integer deptId;
    private Integer addressId;
    private Date dateOfBirth;
}
