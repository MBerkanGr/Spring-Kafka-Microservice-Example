package com.mehmetberkan.addressservice.service;

import com.mehmetberkan.addressservice.model.Address;
import com.mehmetberkan.addressservice.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void save(Address address) {
        Address savedAddress = addressRepository.save(address);
        System.out.println("The address has been successfully saved ! Address : " + address);
    }

    public Address getAddressByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

}
