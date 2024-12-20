package com.vats.projects.employee.management.system.dto;

import lombok.Data;

@Data
public class AuditLogDTO {
    private Integer id;
    private String entityName;
    private Integer entityId;
    private String action;
    private String changedBy;
    private String timestamp;
}
