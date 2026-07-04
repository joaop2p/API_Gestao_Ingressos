package com.example.demo.models.entities;

import com.example.demo.models.dto.absolute.EventDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_events")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local location;
    @Column(nullable = false)
    private LocalDateTime start;
    @Column(nullable = false)
    private LocalDateTime end;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int totalTickets = 0;

    public Event() {}

    public Event(String name, Local location, LocalDateTime start, LocalDateTime end, String description, double price, int totalTickets) {
        this.name = name;
        this.location = location;
        this.start = start;
        this.end = end;
        this.description = description;
        this.price = price;
    }

    public EventDTO toDTO(){
        return new EventDTO(id, name,description, start, end, location.toDTO(), price, totalTickets);
    }
}
