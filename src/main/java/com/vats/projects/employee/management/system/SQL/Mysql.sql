USE employee_management_system;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS files;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS audit_log;

-- Create Address Table
CREATE TABLE addresses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    pincode VARCHAR(20)
);
CREATE INDEX idx_address_city_state ON addresses(city, state);

-- Create Department Table
CREATE TABLE department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) UNIQUE
);

-- Create Employee Table
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    date_of_birth DATE,
    blood_group CHAR(3),
    designation VARCHAR(100),
    salary INT,
    dept_id INT,
    address_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(id),
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);
CREATE INDEX idx_employee_dept_id ON employee(dept_id);
CREATE INDEX idx_employee_address_id ON employee(address_id);

-- Create Files Table
CREATE TABLE files (
    id INT PRIMARY KEY AUTO_INCREMENT,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(50),
    file_url TEXT NOT NULL,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    employee_id INT,
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);

-- Create Roles Table
CREATE TABLE roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE -- e.g., ROLE_ADMIN, ROLE_MANAGER, ROLE_EMPLOYEE
);

-- Create Users Table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Create User_Roles Join Table
CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Create Audit Log Table (Optional)
CREATE TABLE audit_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    entity_name VARCHAR(100), -- e.g., Employee, Department
    entity_id INT, -- ID of the entity affected
    action VARCHAR(50), -- e.g., INSERT, UPDATE, DELETE
    changed_by VARCHAR(100), -- Username or admin making the change
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Additional Indexes for Optimization
CREATE INDEX idx_employee_salary ON employee(salary);
CREATE INDEX idx_employee_dept_designation ON employee(dept_id, designation);
