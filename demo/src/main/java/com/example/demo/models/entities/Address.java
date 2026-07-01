package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String zipCode;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name="user_id", unique = true)
    private User user;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "local_id", unique = true)
    private Local location;

    public Address(){}

    public Address(String street, String number, String district, String city, String state, String zipCode) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
