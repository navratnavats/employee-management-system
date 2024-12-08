package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.dto.EmployeeDTO;
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
        return employeeRepository.saveAll(employees);
    }

}
