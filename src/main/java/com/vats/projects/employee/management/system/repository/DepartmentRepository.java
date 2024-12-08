package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.dto.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentDTO, Integer> {
}
