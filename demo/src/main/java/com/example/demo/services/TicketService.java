package com.example.demo.services;

import com.example.demo.models.dto.absolute.EventDTO;
import com.example.demo.models.dto.absolute.TicketDTO;
import com.example.demo.models.dto.absolute.TicketsListResumeDTO;
import com.example.demo.models.dto.forms.TicketFormDTO;
import com.example.demo.models.dto.resume.TicketGeralResumeDTO;
import com.example.demo.models.dto.resume.TicketResumeDTO;
import com.example.demo.models.entities.Event;
import com.example.demo.models.entities.Local;
import com.example.demo.models.entities.Ticket;
import com.example.demo.models.entities.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.exceptions.NotAvailable;
import com.example.demo.utils.exceptions.NotFound;
import com.example.demo.utils.exceptions.ValueError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    final TicketRepository ticketRepository;
    final EventRepository eventRepository;
    final UserRepository userRepository;
    private static final int LIMIT_BY_USER = 5;

    @Transactional
    public TicketResumeDTO create(TicketFormDTO ticketFormDTO) {
        Event event = eventRepository.findById(ticketFormDTO.eventId())
                .orElseThrow(() -> new NotFound("Event not found"));
        List<Ticket> userTickets = ticketRepository.ticketsByUserIdByEventId(ticketFormDTO.userId(), ticketFormDTO.eventId());
        if (
                userTickets.size() + ticketFormDTO.quantity() > LIMIT_BY_USER
        ) {
            throw new NotAvailable("User has reached the ticket limit for this event");
        }
        Local local = event.getLocation();
        if (event.getTotalTickets() + ticketFormDTO.quantity() > local.getCapacity()) {
            throw new NotAvailable("Not enough tickets available");
        }
        User user = userRepository.findById(ticketFormDTO.userId())
                .orElseThrow(() -> new NotFound("User not found"));
        event.setTotalTickets(event.getTotalTickets() + ticketFormDTO.quantity());
        List<TicketDTO> tickets = new ArrayList<>();
        for(int i = 0; i< ticketFormDTO.quantity(); i++) {
            Ticket ticket = new Ticket(event, user);
            ticketRepository.save(ticket);
            tickets.add(new TicketDTO(
                    ticket.getId(),
                    ticket.getPurchaseDate()
            ));
        }
        double totalPaid = ticketFormDTO.quantity() * event.getPrice();
        return new TicketResumeDTO(user.toDTO(),event.toDTO(),tickets, totalPaid);
    }

    public TicketsListResumeDTO getTicketsByUserId(Long userId, Long eventId) {
        if (userId == null && eventId == null) {
            throw new ValueError("User ID or Event ID must be provided");
        }
        if (userId != null && userRepository.findById(userId).isEmpty()) {
            throw new NotFound("User not found");
        }
        if (eventId != null && eventRepository.findById(eventId).isEmpty()) {
            throw new NotFound("Event not found");
        }
        List<Ticket> tickets = ticketRepository.findTickets(userId, eventId);
        List<TicketResumeDTO> ticketResumes = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketResumes.add(new TicketResumeDTO(
                    ticket.getUser().toDTO(),
                    ticket.getEvent().toDTO(),
                    List.of(new TicketDTO(ticket.getId(), ticket.getPurchaseDate())),
                    ticket.getEvent().getPrice()
            ));
        }
        return new TicketsListResumeDTO(ticketResumes);
    }

    public TicketGeralResumeDTO geralSummary() {
        EventDTO biggestEvent = ticketRepository.findBiggestEvent().orElseThrow(
                () -> new NotFound("No events found")
        ).toDTO();
        int totalSold = ticketRepository.countAllTickets();
        double revenue = ticketRepository.sumAllTicketsRevenue();
        double mean = ticketRepository.meanTicketsPerEvent();
        return new TicketGeralResumeDTO(totalSold, revenue, mean, biggestEvent);
    }
}
