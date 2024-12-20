package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.dto.RoleDTO;
import com.vats.projects.employee.management.system.dto.UserDTO;
import com.vats.projects.employee.management.system.entity.Role;
import com.vats.projects.employee.management.system.entity.User;
import com.vats.projects.employee.management.system.repository.RoleRepository;
import com.vats.projects.employee.management.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired RoleRepository roleRepository;

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Set<Role> roles = userDTO.getRoles().stream().map(roleDTO -> {
            Role role = roleRepository.findByName(roleDTO.getName());
            if (role == null) {
                role = new Role();
                role.setName(roleDTO.getName());
                role = roleRepository.save(role);
            }
            return role;
        }).collect(Collectors.toSet());

        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return mapToDTO(savedUser);
    }



    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        return mapToDTO(user);
    }


    private Set<RoleDTO> mapToRoleDTO(Set<Role> roles) {
        Set<RoleDTO> roleDTOSet = new HashSet<>();
        roles.stream().forEach(role -> {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(role.getId());
            roleDTO.setName(role.getName());
            roleDTOSet.add(roleDTO);
        });
        return roleDTOSet;
    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(mapToRoleDTO(user.getRoles()));
        return userDTO;
    }
}
