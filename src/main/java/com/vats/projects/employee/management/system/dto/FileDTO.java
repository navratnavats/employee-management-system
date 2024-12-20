package com.vats.projects.employee.management.system.dto;

import lombok.Data;

@Data
public class FileDTO {
    private Integer id;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private String uploadedAt;
    private Integer employeeId;
}
