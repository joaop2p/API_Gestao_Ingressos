package com.example.demo.repository;

import com.example.demo.models.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocalRepository extends JpaRepository<Local, Long> {
    boolean existsByName(String name);

    @Query("SELECT COUNT(l) > 0 FROM Local l " +
            "WHERE l.address.street = :street " +
            "AND l.address.number = :number " +
            "AND l.address.district = :district " +
            "AND l.address.city = :city " +
            "AND l.address.state = :state " +
            "AND l.address.zipCode = :zipCode")
    boolean existsByAddress(
            @Param("street") String street,
            @Param("number") String number,
            @Param("district") String district,
            @Param("city") String city,
            @Param("state") String state,
            @Param("zipCode") String zipCode
    );
}