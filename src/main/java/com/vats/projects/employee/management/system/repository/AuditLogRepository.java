package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByEntityName(String entityName);
}
