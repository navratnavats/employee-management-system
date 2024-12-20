package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.dto.EmployeeDTO;
import com.vats.projects.employee.management.system.dto.FileDTO;
import com.vats.projects.employee.management.system.entity.*;
import com.vats.projects.employee.management.system.repository.AddressRepository;
import com.vats.projects.employee.management.system.repository.DepartmentRepository;
import com.vats.projects.employee.management.system.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private S3BucketService s3BucketService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    AuditLogService auditLogService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public void deleteFile(Integer id) {
        fileRepository.deleteById(id);
    }


    public void deleteFile(Integer employeeId, List<String> fileName) {
        if(fileName.size()==1){
            deleteSingleFile(employeeId, fileName.get(0));
        }
        else{
            deleteMultipleFiles(employeeId, fileName);
        }
    }

    private void deleteMultipleFiles(Integer employeeID, List<String> fileNames){
        s3BucketService.deleteMultipleFiles(fileNames, employeeID);
        fileNames.stream().forEach(file -> {
            fileRepository.deleteSingleFileByEmployeeIdAndFileName(employeeID, file);
        });
    }

    private void deleteSingleFile(Integer employeeId, String fileName){
        s3BucketService.deleteSingleFile(fileName, employeeId);
        fileRepository.deleteSingleFileByEmployeeIdAndFileName(employeeId, fileName);
    }

    public void deleteAllFiles(Integer employeeID){
        List<File> file = fileRepository.findByEmployeeId(employeeID);
        List<String> fileNames = file.stream().map(File::getFileName).toList();
        deleteMultipleFiles(employeeID, fileNames);
    }

    public List<FileDTO> uploadFiles(MultipartFile[] files, Integer employeeId) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName("File");
        auditLog.setEntityId(employeeId);
        auditLog.setAction("INSERT");
        auditLog.setChangedBy("API_CALL"); // Pass the user performing the action
        auditLog.setTimestamp(new Timestamp(System.currentTimeMillis()));
        auditLogService.saveAuditLog(auditLog);
        if (files.length == 1) {
            return singleFileUpload(files[0], employeeId);
        } else {
            return multipleFileUpload(files, employeeId);
        }
    }

    public List<FileDTO> getFilesByEmployeeId(Integer employeeId) {
        List<FileDTO> fileDTOList = new ArrayList<>();
            fileRepository.findByEmployeeId(employeeId).stream().forEach(file -> {
                fileDTOList.add(mapToDTO(file));
            });
            return fileDTOList;
        }

    private List<FileDTO> multipleFileUpload(MultipartFile[] files, Integer employeeId) {
        List<FileDTO> fileDTOList = new ArrayList<>();
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);

        files = Arrays.stream(files).toArray(MultipartFile[]::new);

        Arrays.stream(files).forEach(file -> {
            String originalFileName = file.getOriginalFilename().trim();
            String fileName = getFileName(employeeId, originalFileName);
            String fileType = file.getContentType();
            String fileUrl = s3BucketService.uploadSingleFile(fileName, file);

            File fileEntity = new File();
            fileEntity.setFileName(fileName);
            fileEntity.setFileType(fileType);
            fileEntity.setFileUrl(fileUrl);
            fileEntity.setEmployee(mapToEmployeeEntity(employeeDTO));

            File savedFile = fileRepository.save(fileEntity);
            fileDTOList.add(mapToDTO(savedFile));
        });

        return fileDTOList;
    }


    private List<FileDTO> singleFileUpload(MultipartFile file, Integer employeeId) {
        String originalFileName = file.getOriginalFilename().trim();
        String fileName = getFileName(employeeId, originalFileName);
        String fileType = file.getContentType();
        String fileUrl = s3BucketService.uploadSingleFile(fileName, file);

        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);

        File fileEntity = new File();
        fileEntity.setFileName(fileName);
        fileEntity.setFileType(fileType);
        fileEntity.setFileUrl(fileUrl);
        fileEntity.setEmployee(mapToEmployeeEntity(employeeDTO));

        File savedFile = fileRepository.save(fileEntity);

        return List.of(mapToDTO(savedFile));
    }

    private static String getFileName(Integer employeeId, String originalFileName) {
        return "EmpID_" + employeeId + "_" + originalFileName.replaceAll(" ", "_");
    }

    private FileDTO mapToDTO(File file) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(file.getId());
        fileDTO.setFileName(file.getFileName());
        fileDTO.setFileType(file.getFileType());
        fileDTO.setFileUrl(file.getFileUrl());
        return fileDTO;
    }


    private Employee mapToEmployeeEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setBloodGroup(employeeDTO.getBloodGroup());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        Address address = addressRepository.findById(employeeDTO.getAddressId()).get();
        employee.setAddress(address);
        Department department = departmentRepository.findById(employeeDTO.getDeptId()).get();
        employee.setDepartment(department);
        return employee;

    }
}


