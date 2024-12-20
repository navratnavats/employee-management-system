package com.vats.projects.employee.management.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "audit_log")
@Getter
@Setter
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_name", nullable = false)
    private String entityName;

    @Column(name = "entity_id")
    private Integer entityId;

    @Column(name = "action", nullable = false)
    private String action; // e.g., INSERT, UPDATE, DELETE

    @Column(name = "changed_by", nullable = false)
    private String changedBy;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
}
