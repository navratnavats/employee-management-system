package com.vats.projects.employee.management.system.dto;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
public class DepartmentDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "head_of_department")
    private Integer headOfDepartment;

}
