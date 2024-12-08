package com.vats.projects.employee.management.system.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class EmployeeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "date_of_birth")
    private java.sql.Date dateOfBirth;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "designation")
    private String designation;

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id", nullable = false)
    private DepartmentDTO department;

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private AddressDTO address;

    // One employee can have many files
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FileDTO> files;
}
