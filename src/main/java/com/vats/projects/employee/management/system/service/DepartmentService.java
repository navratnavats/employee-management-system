package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.entity.Department;
import com.vats.projects.employee.management.system.dto.DepartmentDTO;
import com.vats.projects.employee.management.system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = mapToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return mapToDTO(savedDepartment);
    }

    public DepartmentDTO getDepartmentById(Integer id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
        return mapToDTO(department);
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    private Department mapToEntity(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setName(departmentDTO.getName());
        return department;
    }

    private DepartmentDTO mapToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        return departmentDTO;
    }
}
