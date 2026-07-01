package com.example.demo.models.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Address address;

    public User() {}

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
