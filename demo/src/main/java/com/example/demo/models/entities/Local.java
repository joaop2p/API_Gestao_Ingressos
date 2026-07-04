package com.example.demo.models.entities;


import com.example.demo.models.dto.forms.LocalFormDTO;
import com.example.demo.models.dto.absolute.LocalDTO;
import com.example.demo.models.others.LocalTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_locals")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private LocalTypes type;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private java.util.List<Event> events;
    @Column(nullable = false)
    @Min(1)
    private int capacity;

    public Local() {}

    public Local(String name, LocalTypes type, int capacity) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }

    public static Local fromDTOForm(LocalFormDTO localFormDTO){
        Local local = new Local();
        local.name = localFormDTO.name();
        local.type = localFormDTO.type();
        local.capacity = localFormDTO.capacity();
        local.address = Address.fromFormDTO(localFormDTO.address());
        return local;
    }

    public LocalDTO toDTO() {
        return new LocalDTO(id, name, address.toDTO(), capacity, type);
    }

    public LocalFormDTO toFormDTO() {
        return new LocalFormDTO(name, address.toFormDTO(), capacity, type);
    }
}
