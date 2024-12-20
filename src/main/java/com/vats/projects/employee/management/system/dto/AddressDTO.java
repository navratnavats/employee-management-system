package com.vats.projects.employee.management.system.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Integer id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
