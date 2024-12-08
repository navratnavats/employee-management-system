package com.vats.projects.employee.management.system.controller;

import com.vats.projects.employee.management.system.dto.EmployeeDTO;
import com.vats.projects.employee.management.system.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //create
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Validated EmployeeDTO employee){
        if(employee != null){
            EmployeeDTO employeeDTO = employeeService.createEmployee(employee);
            return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    //update
    @PutMapping("/update")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Validated EmployeeDTO employee){
        if(employee != null){
            EmployeeDTO employeeDTO = employeeService.updateEmployee(employee);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployeeById(@PathVariable int id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<EmployeeDTO>> addEmployeesInBulk(@RequestBody List<EmployeeDTO> employees) {
        return ResponseEntity.ok(employeeService.addEmployeesInBulk(employees));
    }

}
