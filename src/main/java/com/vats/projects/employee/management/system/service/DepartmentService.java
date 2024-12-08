package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.dto.DepartmentDTO;
import com.vats.projects.employee.management.system.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDTO createDepartment(DepartmentDTO department) {
        return departmentRepository.save(department);
    }

    public DepartmentDTO updateDepartment(Integer id, DepartmentDTO department) {
        DepartmentDTO existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        existing.setName(department.getName());
        existing.setHeadOfDepartment(department.getHeadOfDepartment());
        return departmentRepository.save(existing);
    }

    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    public DepartmentDTO getDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll();
    }
    public List<DepartmentDTO> addDepartmentsInBulk(List<DepartmentDTO> departments) {
        return departmentRepository.saveAll(departments);
    }

}
