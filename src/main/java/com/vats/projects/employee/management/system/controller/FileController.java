package com.vats.projects.employee.management.system.controller;

import com.vats.projects.employee.management.system.dto.FileDTO;
import com.vats.projects.employee.management.system.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * Upload files for an employee.
     * Handles both single and multiple file uploads.
     *
     * @param files      Multipart file array for upload
     * @param employeeId ID of the employee
     * @return List of FileDTO representing uploaded files
     */
    @PostMapping("/upload")
    public ResponseEntity<List<FileDTO>> uploadFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("employeeId") Integer employeeId) {
        List<FileDTO> fileDTOs = fileService.uploadFiles(files, employeeId);
        return ResponseEntity.ok(fileDTOs);
    }

    /**
     * Get all files associated with a specific employee.
     *
     * @param employeeId ID of the employee
     * @return List of FileDTO representing employee files
     */
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<FileDTO>> getFilesByEmployeeId(@PathVariable Integer employeeId) {
        List<FileDTO> fileDTOs = fileService.getFilesByEmployeeId(employeeId);
        return ResponseEntity.ok(fileDTOs);
    }

    /**
     * Delete a specific file for an employee.
     *
     * @param employeeId ID of the employee
     * @param fileName   Name of the file to delete
     * @return HTTP status indicating the result
     */

    @DeleteMapping("/employee/{employeeId}/file")
    public ResponseEntity<Void> deleteFilesForEmployee(
            @PathVariable Integer employeeId,
            @RequestParam("fileName") List<String> fileName) {
        fileService.deleteFile(employeeId, fileName);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete all files associated with an employee.
     *
     * @param employeeId ID of the employee
     * @return HTTP status indicating the result
     */
    @DeleteMapping("/employee/{employeeId}/files")
    public ResponseEntity<Void> deleteAllFilesForEmployee(@PathVariable Integer employeeId) {
        fileService.deleteAllFiles(employeeId);
        return ResponseEntity.noContent().build();
    }
}
