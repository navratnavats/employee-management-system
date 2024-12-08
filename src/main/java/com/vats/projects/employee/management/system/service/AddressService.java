package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.dto.AddressDTO;
import com.vats.projects.employee.management.system.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDTO createAddress(AddressDTO address) {
        return addressRepository.save(address);
    }

    public AddressDTO updateAddress(Integer id, AddressDTO address) {
        AddressDTO existing = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        existing.setStreet(address.getStreet());
        existing.setCity(address.getCity());
        existing.setState(address.getState());
        existing.setCountry(address.getCountry());
        existing.setPincode(address.getPincode());
        return addressRepository.save(existing);
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    public AddressDTO getAddressById(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll();
    }
    public List<AddressDTO> addAddressesInBulk(List<AddressDTO> addresses) {
        return addressRepository.saveAll(addresses);
    }

}
