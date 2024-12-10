package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.dto.AddressDTO;
import com.vats.projects.employee.management.system.dto.DepartmentDTO;
import com.vats.projects.employee.management.system.dto.EmployeeDTO;
import com.vats.projects.employee.management.system.repository.AddressRepository;
import com.vats.projects.employee.management.system.repository.DepartmentRepository;
import com.vats.projects.employee.management.system.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        int id = employee.getId();
        Optional<EmployeeDTO> employeeDTO = employeeRepository.findById(id);
        if (employeeDTO.isPresent()) {
            employeeRepository.save(employee);
        }
        return employee;
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }


    public EmployeeDTO getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public List<EmployeeDTO> addEmployeesInBulk(List<EmployeeDTO> employees) {
        for (EmployeeDTO employee : employees) {
            System.out.println(employee.getAddress().getId());
            AddressDTO address = addressRepository.findById(employee.getAddress().getId())
                    .orElseThrow(() -> new RuntimeException("Address not found with id: " + employee.getAddress().getId()));
            DepartmentDTO department = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + employee.getDepartment().getId()));

            employee.setAddress(address);
            employee.setDepartment(department);

            employeeRepository.save(employee);
        }

        return employees;
    }

}
