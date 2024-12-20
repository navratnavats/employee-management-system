package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE (:departmentId IS NULL OR e.department.id = :departmentId) " +
            "AND (:minSalary IS NULL OR e.salary >= :minSalary) " +
            "AND (:maxSalary IS NULL OR e.salary <= :maxSalary)")
    Page<Employee> searchEmployees(@Param("departmentId") Integer departmentId,
                                   @Param("minSalary") Integer minSalary,
                                   @Param("maxSalary") Integer maxSalary,
                                   Pageable pageable);
}
