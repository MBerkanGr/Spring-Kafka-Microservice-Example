package com.mehmetberkan.addressservice.controller;

import com.mehmetberkan.addressservice.model.Address;
import com.mehmetberkan.addressservice.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/address")
@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{userId}")
    public Address getAddressByUserId(@PathVariable Long userId) {
        return addressService.getAddressByUserId(userId);
    }

}
