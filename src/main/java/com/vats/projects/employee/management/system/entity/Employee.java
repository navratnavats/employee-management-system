package com.vats.projects.employee.management.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "designation")
    private String designation;

    @Column(name = "salary")
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
