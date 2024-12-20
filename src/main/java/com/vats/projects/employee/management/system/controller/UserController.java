package com.vats.projects.employee.management.system.controller;

import com.vats.projects.employee.management.system.dto.UserDTO;
import com.vats.projects.employee.management.system.entity.User;
import com.vats.projects.employee.management.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

//    for internal purpose for admin to create account
    @PostMapping("/internal/create/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }
}
