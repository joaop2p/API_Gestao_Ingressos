package com.example.demo.models.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
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

    public Event() {}

    public Event(String name, Local location, LocalDateTime start, LocalDateTime end, String description) {
        this.name = name;
        this.location = location;
        this.start = start;
        this.end = end;
        this.description = description;
    }
}
