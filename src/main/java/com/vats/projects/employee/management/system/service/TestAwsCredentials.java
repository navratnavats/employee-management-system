package com.vats.projects.employee.management.system.service;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;

public class TestAwsCredentials {
    public static void main(String[] args) {
        try {
            AwsCredentials credentials = DefaultCredentialsProvider.create().resolveCredentials();
            System.out.println("AWS Access Key: " + credentials.accessKeyId());
            System.out.println("AWS Region: " + Region.of(System.getenv("AWS_REGION")));
            System.out.println("AWS Secret Key: " + Region.of(System.getenv("AWS_SECRET_ACCESS_KEY")));

        } catch (Exception e) {
            System.err.println("Failed to load credentials: " + e.getMessage());
        }
    }
}
