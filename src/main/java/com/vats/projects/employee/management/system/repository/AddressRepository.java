package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.dto.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressDTO, Integer> {
}
