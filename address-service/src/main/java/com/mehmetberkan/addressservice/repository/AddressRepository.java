package com.mehmetberkan.addressservice.repository;

import com.mehmetberkan.addressservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUserId(Long userId);
}
