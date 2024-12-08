use employee_management_system;

Create table employee(
	id int primary key,
    first_name varchar(100),
    last_name varchar(100),
    date_of_birth Date,
    blood_group char(3),
    designation varchar(100),
    salary int,
    dept_id int references department.id,
    address_id int references address.id
);
create index idx_employee_dept_id ON employee(dept_id);
create index idx_employee_address_id ON employee(address_id);


CREATE TABLE department (
    id INT PRIMARY KEY,
    name VARCHAR(100) UNIQUE,
    head_of_department INT
);
CREATE UNIQUE INDEX idx_department_name ON department(name);
CREATE INDEX idx_department_head ON department(head_of_department);

-- Address Table
CREATE TABLE address (
    id INT PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    pincode VARCHAR(20)
);
CREATE INDEX idx_address_city_state ON address(city, state);
