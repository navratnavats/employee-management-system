package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.dto.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Integer> {
}
