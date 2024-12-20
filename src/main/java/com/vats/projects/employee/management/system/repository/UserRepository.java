package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
