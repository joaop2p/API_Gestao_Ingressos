package com.example.demo.models.entities;

import com.example.demo.models.dto.absolute.TicketDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tb_tickets")
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Instant purchaseDate = Instant.now();


    public Ticket() {}

    public Ticket(Event event, User user) {
        this.event = event;
        this.user = user;
        this.purchaseDate = Instant.now();
    }

//    public TicketDTO toDTO() {
//        return new TicketDTO(id, event.toDTO(), user.toDTO(), purchaseDate, paid);
//    }
}
