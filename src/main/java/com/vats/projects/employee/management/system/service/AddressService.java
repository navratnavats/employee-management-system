package com.vats.projects.employee.management.system.service;

import com.vats.projects.employee.management.system.entity.Address;
import com.vats.projects.employee.management.system.dto.AddressDTO;
import com.vats.projects.employee.management.system.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address = mapToEntity(addressDTO);
        Address savedAddress = addressRepository.save(address);
        return mapToDTO(savedAddress);
    }

    public AddressDTO getAddressById(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
        return mapToDTO(address);
    }

    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    private Address mapToEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setPincode(addressDTO.getPincode());
        return address;
    }

    private AddressDTO mapToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setPincode(address.getPincode());
        return addressDTO;
    }
}
