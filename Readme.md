# Employee Management System

## Overview

The **Employee Management System** is a Spring Boot application designed to manage employee records, including their personal details, department associations, and uploaded files. It provides RESTful APIs for CRUD operations on employees, departments, and addresses, as well as file upload functionalities using an AWS S3 bucket for storage.

---

## Features

1. **Employee Management**:
    - Add, update, delete, and view employee details.
    - Associate employees with departments and addresses.
    - Fetch a list of all employees or an individual employee by ID.

2. **Department Management**:
    - Add, update, delete, and fetch department details.
    - Categorize departments into different types (e.g., core engineering, client technology).

3. **Address Management**:
    - Add, update, delete, and view addresses.
    - Link employees with specific addresses.

4. **File Management**:
    - Upload single or multiple files for an employee.
    - Retrieve and delete files for an employee.
    - Store file metadata in a database and actual files in AWS S3.

---

## Technologies Used

- **Java 21**
- **Spring Boot 3.x**
- **Hibernate (JPA)** for ORM
- **MySQL** for data persistence
- **AWS S3** for file storage
- **Lombok** for reducing boilerplate code
- **Maven** for dependency management
- **Postman** for API testing

---

## Database Schema

### Tables:

1. **Employee Table**:
    - Stores employee details, including references to department and address.

2. **Department Table**:
    - Stores department information and categorization.

3. **Address Table**:
    - Stores detailed address information.

4. **Files Table**:
    - Stores metadata of uploaded files, including file names, types, and URLs.

---

## API Endpoints

### **Employee APIs**
- **POST** `/employees`: Create a new employee.
- **GET** `/employees`: Fetch all employees.
- **GET** `/employees/{id}`: Fetch employee details by ID (includes department, address, and files).
- **PUT** `/employees/{id}`: Update employee details.
- **DELETE** `/employees/{id}`: Delete an employee.

### **Department APIs**
- **POST** `/departments`: Create a new department.
- **GET** `/departments`: Fetch all departments.
- **GET** `/departments/{id}`: Fetch a department by ID.
- **PUT** `/departments/{id}`: Update department details.
- **DELETE** `/departments/{id}`: Delete a department.

### **Address APIs**
- **POST** `/addresses`: Create a new address.
- **GET** `/addresses`: Fetch all addresses.
- **GET** `/addresses/{id}`: Fetch an address by ID.
- **PUT** `/addresses/{id}`: Update address details.
- **DELETE** `/addresses/{id}`: Delete an address.

### **File APIs**
- **POST** `/files/upload`: Upload single or multiple files for an employee.
- **GET** `/employees/{id}/files`: Fetch all files for an employee.
- **DELETE** `/files/delete/{employeeId}`: Delete specific files for an employee.

---

## File Upload

### AWS S3 Integration
- Files are stored in an AWS S3 bucket.
- Metadata, including file name, type, and URL, is stored in the `files` table.

### Upload API
- Single or multiple files can be uploaded via the `/files/upload` endpoint.

Example cURL for file upload:
```bash
curl -X POST -F "employeeId=123" -F "files=@path/to/file1.pdf" -F "files=@path/to/file2.jpg" http://localhost:8080/files/upload
```

---

## Prerequisites

1. **MySQL**: Create a database named `employee_management_system`.
2. **AWS S3 Bucket**: Create a bucket and set appropriate policies for file uploads.
3. **Java 21**: Ensure Java 21 is installed.
4. **Maven**: Install Maven for building the project.

---

## Getting Started

### Clone the Repository
```bash
git clone <repository-url>
cd employee-management-system
```

### Configure Application Properties
Update the `application.properties` file:
```properties
spring.application.name=employee-management-system
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management_system
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# AWS S3 Configuration
aws.accessKey=your-access-key
aws.secretKey=your-secret-key
aws.s3.bucketName=your-bucket-name
aws.region=your-region
```

### Build and Run the Application
```bash
mvn clean install
mvn spring-boot:run
```

---

## Testing the Application

1. Use **Postman** or similar tools to test APIs.
2. Validate database entries via MySQL Workbench or CLI.
3. Verify file uploads in your AWS S3 bucket.

---

## Future Enhancements

1. **Role-Based Access Control**:
    - Add user roles for different levels of access (e.g., admin, manager).

2. **Email Notifications**:
    - Send notifications for events like employee creation or file uploads.

3. **Search and Filter**:
    - Add search and filter functionality for employees and departments.

4. **Front-End Integration**:
    - Develop a front-end interface for better user experience.

---

## License

This project is licensed under the MIT License.

---
