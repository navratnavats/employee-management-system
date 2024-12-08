USE employee_management_system;

-- Drop existing tables

DROP table if exists files;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS addresses;




-- Create Address Table
CREATE TABLE addresses (
    id INT PRIMARY KEY auto_increment,
    street VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    pincode VARCHAR(20)
);
CREATE INDEX idx_address_city_state ON addresses(city, state);

-- Create Department Table
CREATE TABLE department (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(100) UNIQUE
);

-- Create Employee Table
CREATE TABLE employee (
    id INT PRIMARY KEY auto_increment,
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

create table files(
	id int primary key auto_increment,
    file_name varchar(255) not null,
    file_type varchar(50),
    file_url text not null,
    uploaded_at timestamp default current_timestamp,
    employee_id int,
    foreign key (employee_id) references employee(id)
);

CREATE INDEX idx_employee_dept_id ON employee(dept_id);
CREATE INDEX idx_employee_address_id ON employee(address_id);



