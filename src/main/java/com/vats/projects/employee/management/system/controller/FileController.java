package com.vats.projects.employee.management.system.controller;

import com.vats.projects.employee.management.system.dto.EmployeeDTO;
import com.vats.projects.employee.management.system.dto.FileDTO;
import com.vats.projects.employee.management.system.service.EmployeeService;
import com.vats.projects.employee.management.system.service.FileService;
import com.vats.projects.employee.management.system.service.S3BucketService;
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

    @Autowired
    EmployeeService employeeService;

    @Autowired
    S3BucketService s3BucketService;

    @PostMapping("/upload")
    public ResponseEntity<List<FileDTO>> uploadFile(@RequestParam("file") MultipartFile[] file, @RequestParam("employeeId") Integer employeeId) {
        List<FileDTO> fileDTO = fileService.uploadFile(file, employeeId);
        return new ResponseEntity<>(fileDTO, HttpStatus.OK);
    }


    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<FileDTO>> getFilesByEmployeeId(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(fileService.getFilesByEmployeeId(employeeId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSingleFileForParticularEmployee(@PathVariable Integer id, @RequestParam String fileName) {
        fileService.deleteSingleFileForParticularEmployee(id, fileName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/all/{employeeId}")
    public ResponseEntity<Void> deleteMultipleFiles(@PathVariable Integer employeeId){
        fileService.deleteMultipleFiles(employeeId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
