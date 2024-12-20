package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
