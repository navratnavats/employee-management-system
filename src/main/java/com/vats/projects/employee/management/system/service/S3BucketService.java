package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.dto.FileDTO;
import com.vats.projects.employee.management.system.entity.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class S3BucketService {

    @Value(value = "${aws.bucket.name}")
    private String bucketName;

    private final S3Client s3Client;

    public S3BucketService(){
        this.s3Client = S3Client.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public void deleteSingleFile(String fileName, Integer employeeId){
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        s3Client.deleteObject(deleteObjectRequest);

        log.info("File deleted for employeeId: {} from s3 bucket: {} ", employeeId, fileName);
    }


    public void deleteMultipleFiles(List<String> fileDetailsListForEmployee, Integer employeeId) {

        List<ObjectIdentifier> objectIdentifierList = fileDetailsListForEmployee
                .stream().map(k -> ObjectIdentifier.builder().key(k).build())
                .collect(Collectors.toList());

        DeleteObjectsRequest deleteObjectsRequest = DeleteObjectsRequest.builder()
                .bucket(bucketName)
                .delete(Delete.builder().objects(objectIdentifierList).build())
                .build();

        s3Client.deleteObjects(deleteObjectsRequest);

        log.info("All the files deleted for employeeId: {} ", employeeId);
    }

    public String uploadSingleFile(String fileName, MultipartFile file) {
        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "https://" + bucketName + ".s3.eu-north-1.amazonaws.com/" + fileName;
    }
}
