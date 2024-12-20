package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.entity.Role;
import com.vats.projects.employee.management.system.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
