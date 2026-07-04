package com.example.demo.models.entities;

import com.example.demo.models.dto.absolute.AddressDTO;
import com.example.demo.models.dto.forms.AddressFormDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_addresses")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String zipCode;

    public Address(){}

    public Address(String street, String number, String district, String city, String state, String zipCode) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public AddressDTO toDTO() {
        return new AddressDTO(id, street, number, district, city, state, zipCode);
    }

    public AddressFormDTO toFormDTO() {
        return new AddressFormDTO(street, number, district, city, state, zipCode);
    }

    public static Address fromFormDTO(AddressFormDTO addressFormDTO) {
        return new Address(
                addressFormDTO.street(),
                addressFormDTO.number(),
                addressFormDTO.district(),
                addressFormDTO.city(),
                addressFormDTO.state(),
                addressFormDTO.zipCode()
        );
    }
}
