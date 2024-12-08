package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.dto.EmployeeDTO;
import com.vats.projects.employee.management.system.dto.FileDTO;
import com.vats.projects.employee.management.system.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    S3BucketService s3BucketService;

    @Autowired
    EmployeeService employeeService;

    public FileDTO uploadFile(FileDTO file) {
        return fileRepository.save(file);
    }

    public List<FileDTO> getFilesByEmployeeId(Integer employeeId) {
        return fileRepository.findByEmployeeId(employeeId);
    }

    public void deleteFile(Integer id) {
        fileRepository.deleteById(id);
    }

    public void deleteMultipleFiles(Integer employeeId){
        List<FileDTO> fileDetailsListForEmployee = fileRepository.findByEmployeeId(employeeId);
        s3BucketService.deleteMultipleFiles(fileDetailsListForEmployee, employeeId);
        fileRepository.deleteAllByEmployeeId(employeeId);
    }

    public void deleteSingleFileForParticularEmployee(Integer employeeId, String fileName) {
        s3BucketService.deleteSingleFile(fileName, employeeId);
        fileRepository.deleteSingleFileForParticularEmployee(employeeId, fileName);
    }

    public List<FileDTO> uploadFile(MultipartFile[] file, Integer employeeId) {
        if (file.length == 1)
            return singleFileUpload(file[0], employeeId);
        else
            return mutipleFileUpload(file, employeeId);
    }

    private List<FileDTO> mutipleFileUpload(MultipartFile[] files, Integer employeeId) {
        List<FileDTO> fileDTOList = new ArrayList<>();
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId); // Add this method in EmployeeService
        Arrays.stream(files).forEach(file ->{
            String originalFileName = file.getOriginalFilename().trim();
            String fileName = getFileName(employeeId, originalFileName);
            String fileType = file.getContentType();

            String fileUrl = s3BucketService.uploadSingleFile(fileName, file);

            // Create FileDTO object
            FileDTO fileDTO = setFileDTO(fileName, fileType, fileUrl, employee);

            fileDTOList.add(fileDTO);

            fileRepository.save(fileDTO);
        });
        return fileDTOList;
    }


    private List<FileDTO> singleFileUpload(MultipartFile file, Integer employeeId) {
        String originalFileName = file.getOriginalFilename().trim();
        String fileName = getFileName(employeeId, originalFileName);
        String fileType = file.getContentType();
        String fileUrl = s3BucketService.uploadSingleFile(fileName, file); // Assume file is stored locally in /uploads

        // Fetch the employee from the database
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId); // Add this method in EmployeeService

        // Create FileDTO object
        FileDTO fileDTO = setFileDTO(fileName, fileType, fileUrl, employee);

        fileRepository.save(fileDTO);

        return (List<FileDTO>) fileDTO;
    }

    private static String getFileName(Integer employeeId, String originalFileName) {
        String fileName = "EmpID_"+ employeeId +"_"+ originalFileName.replaceAll(" ", "_");
        return fileName;
    }


    private static FileDTO setFileDTO(String fileName, String fileType, String fileUrl, EmployeeDTO employee) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(fileName);
        fileDTO.setFileType(fileType);
        fileDTO.setFileUrl(fileUrl);
        fileDTO.setEmployee(employee);
        return fileDTO;
    }
}
