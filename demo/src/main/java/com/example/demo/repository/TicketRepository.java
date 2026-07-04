package com.example.demo.repository;

import com.example.demo.models.entities.Event;
import com.example.demo.models.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository  extends JpaRepository<Ticket, Long> {

    public boolean existsById(Long id);

    @Query("""
        SELECT COUNT(t) FROM Ticket t WHERE t.event.id = :eventId
    """
    )
    public int soldTicketsByEventId(Long eventId);

    @Query("""
        SELECT t FROM Ticket t WHERE t.user.id = :userId AND t.event.id = :eventId
    """
    )
    public List<Ticket> ticketsByUserIdByEventId(@Param("userId") Long userId, @Param("eventId") Long eventId);

    @Query("""
    SELECT t FROM Ticket t
    WHERE (:userId IS NULL OR t.user.id = :userId)
      AND (:eventId IS NULL OR t.event.id = :eventId)
""")
    List<Ticket> findTickets(
            @Param("userId") Long userId,
            @Param("eventId") Long eventId
    );

    @Query("SELECT COUNT(t) FROM Ticket t")
    public int countAllTickets();

    @Query("SELECT SUM(t.event.price) FROM Ticket t")
    public double sumAllTicketsRevenue();

    //- Taxa de ocupação média dos eventos (ingressos vendidos / capacidade máxima).
    @Query("SELECT AVG(t.event.totalTickets * 1.0 / t.event.location.capacity) FROM Ticket t")
    public double meanTicketsPerEvent();

    @Query("SELECT t.event FROM Ticket t GROUP BY t.event ORDER BY COUNT(t) DESC")
    public Optional<Event> findBiggestEvent();
}
