package com.vats.projects.employee.management.system.controller;

import com.vats.projects.employee.management.system.dto.EmployeeDTO;
import com.vats.projects.employee.management.system.dto.NewUserRequest;
import com.vats.projects.employee.management.system.dto.RoleDTO;
import com.vats.projects.employee.management.system.dto.UserDTO;
import com.vats.projects.employee.management.system.service.EmployeeService;
import com.vats.projects.employee.management.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createOrUpdateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeDTO>> searchEmployees(
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) Integer minSalary,
            @RequestParam(required = false) Integer maxSalary,
            Pageable pageable) {
        return ResponseEntity.ok(employeeService.searchEmployees(departmentId, minSalary, maxSalary, pageable));
    }


}
