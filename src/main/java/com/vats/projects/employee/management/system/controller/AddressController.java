package com.vats.projects.employee.management.system.controller;

import com.vats.projects.employee.management.system.dto.AddressDTO;
import com.vats.projects.employee.management.system.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> createOrUpdateAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok(addressService.saveAddress(addressDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
