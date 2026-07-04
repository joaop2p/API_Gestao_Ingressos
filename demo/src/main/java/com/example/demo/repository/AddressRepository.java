package com.example.demo.repository;

import com.example.demo.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findById(UUID id);
    Optional<Address> findByStreetAndCityAndStateAndZipCode(String street, String city, String state, String zipCode);
}
