package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.entity.AuditLog;
import com.vats.projects.employee.management.system.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Save Audit Log
    public AuditLog saveAuditLog(AuditLog log) {
        return auditLogRepository.save(log);
    }

    // Get Logs by Entity Name
    public List<AuditLog> getLogsByEntityName(String entityName) {
        return auditLogRepository.findByEntityName(entityName);
    }
}
