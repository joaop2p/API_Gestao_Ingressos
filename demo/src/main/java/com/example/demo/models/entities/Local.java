package com.example.demo.models.entities;


import com.example.demo.models.others.LocalTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "locals")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private LocalTypes type;
    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Address address;
    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private java.util.List<Event> events;
    @Column(nullable = false)
    private int capacity;

    public Local() {}

    public Local(String name, LocalTypes type) {
        this.name = name;
        this.type = type;
    }
}
