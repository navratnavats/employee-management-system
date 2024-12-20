package com.vats.projects.employee.management.system.controller;

import com.vats.projects.employee.management.system.dto.*;
import com.vats.projects.employee.management.system.service.UserService;
import com.vats.projects.employee.management.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        String token = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
        return new TokenResponse(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody NewUserRequest newUserRequest) {
        String userName = newUserRequest.getUsername();
        String password = newUserRequest.getPassword();
        Set<RoleDTO> roleDTOSet = newUserRequest.getRoleDTO();
        UserDTO newUser = new UserDTO();

        if(userService.userExists(userName)){
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }
        else{

            newUser.setUsername(userName);
            newUser.setPassword(password);
            newUser.setRoles(roleDTOSet);

        }
        return new ResponseEntity<UserDTO>(userService.saveUser(newUser) , HttpStatus.OK);
    }


}
