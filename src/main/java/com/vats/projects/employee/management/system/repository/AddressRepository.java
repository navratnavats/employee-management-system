package com.vats.projects.employee.management.system.repository;

import com.vats.projects.employee.management.system.dto.AddressDTO;
import com.vats.projects.employee.management.system.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
