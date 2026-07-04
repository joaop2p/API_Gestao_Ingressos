package com.example.demo.repository;

import com.example.demo.models.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("""
        select (count(e) > 0)
        from Event e
        where e.location.id = :localId
          and e.start < :end
          and e.end > :start
    """)
    boolean existsConflict(
            @Param("localId") Long localId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
        select (count(e) > 0)
        from Event e
        where e.location.id = :localId
          and e.start < :end
          and e.end > :start
    """)
    boolean existsConflictForUpdate(
            @Param("localId") Long localId,
            @Param("newStart") LocalDateTime newStart,
            @Param("newEnd") LocalDateTime newEnd
    );



    @Query("""
        select (e.totalTickets - e.totalTickets)
        from Event e
        where e.id = :eventId
    """)
    int getAvailableTickets(@Param("eventId") Long eventId);
}