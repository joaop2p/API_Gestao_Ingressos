package com.example.demo.services;

import com.example.demo.models.dto.AddressFormDTO;
import com.example.demo.models.dto.UserFormDTO;
import com.example.demo.models.entities.Address;
import com.example.demo.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public boolean alreadyExists(AddressFormDTO addressFormDTO) {
        return addressRepository.findByStreetAndNumberAndCityAndStateAndZipCode(
                addressFormDTO.street(),
                addressFormDTO.number(),
                addressFormDTO.city(),
                addressFormDTO.state(),
                addressFormDTO.zipCode()
        ).isPresent();
    }

    public Address createNewAddress(AddressFormDTO addressFormDTO) {
        if (alreadyExists(addressFormDTO)) {
            throw new IllegalArgumentException("This address already exists.");
        }
        Address address = new Address();
        address.setStreet(addressFormDTO.street());
        address.setNumber(addressFormDTO.number());
        address.setCity(addressFormDTO.city());
        address.setState(addressFormDTO.state());
        address.setZipCode(addressFormDTO.zipCode());
        return addressRepository.save(address);
    }
}
