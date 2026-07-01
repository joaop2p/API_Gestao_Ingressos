package com.example.demo.repository;

import com.example.demo.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByStreetAndNumberAndCityAndStateAndZipCode(
            String street,
            String number,
            String city,
            String state,
            String zipCode
    );

}
