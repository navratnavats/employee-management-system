package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.entity.Address;
import com.vats.projects.employee.management.system.entity.AuditLog;
import com.vats.projects.employee.management.system.entity.Department;
import com.vats.projects.employee.management.system.entity.Employee;
import com.vats.projects.employee.management.system.dto.EmployeeDTO;
import com.vats.projects.employee.management.system.repository.AddressRepository;
import com.vats.projects.employee.management.system.repository.DepartmentRepository;
import com.vats.projects.employee.management.system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuditLogService auditLogService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    // Create or Update Employee
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName("Employee");
        auditLog.setEntityId(savedEmployee.getId());
        auditLog.setAction("INSERT");
        auditLog.setChangedBy("API_CALL"); // Pass the user performing the action
        auditLog.setTimestamp(new Timestamp(System.currentTimeMillis()));
        auditLogService.saveAuditLog(auditLog);

        return mapToDTO(savedEmployee);
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
        return mapToDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void deleteEmployee(Integer id) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName("Employee");
        auditLog.setEntityId(id);
        auditLog.setAction("DELETE");
        auditLog.setChangedBy("API_CALL"); // Pass the user performing the action
        auditLog.setTimestamp(new Timestamp(System.currentTimeMillis()));
        auditLogService.saveAuditLog(auditLog);

        employeeRepository.deleteById(id);
    }

    public Page<EmployeeDTO> searchEmployees(Integer departmentId, Integer minSalary, Integer maxSalary, Pageable pageable) {
        Page<Employee> employees = employeeRepository.searchEmployees(departmentId, minSalary, maxSalary, pageable);
        return employees.map(this::mapToDTO);
    }

    private Employee mapToEntity(EmployeeDTO employeeDTO) {
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

    private EmployeeDTO mapToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDeptId(employee.getDepartment().getId());
        employeeDTO.setAddressId(employee.getAddress().getId());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setDesignation(employee.getDesignation());
        employeeDTO.setBloodGroup(employee.getBloodGroup());
        return employeeDTO;
    }
}
