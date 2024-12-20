package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
