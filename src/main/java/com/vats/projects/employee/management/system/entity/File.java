package com.vats.projects.employee.management.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "files")
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "uploaded_at", nullable = false)
    private Timestamp uploadedAt;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
