package com.example.demo.repository;

import com.example.demo.models.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("""
        select (count(e) > 0)
        from Event e
        where e.location.id = :localId
          and e.start < :newEnd
          and e.end > :newStart
    """)
    boolean existsConflict(
            @Param("localId") Long localId,
            @Param("newStart") LocalDateTime newStart,
            @Param("newEnd") LocalDateTime newEnd
    );

    @Query("""
        select (count(e) > 0)
        from Event e
        where e.location.id = :localId
          and e.id <> :eventId
          and e.start < :newEnd
          and e.end > :newStart
    """)
    boolean existsConflictForUpdate(
            @Param("eventId") Long eventId,
            @Param("localId") Long localId,
            @Param("newStart") LocalDateTime newStart,
            @Param("newEnd") LocalDateTime newEnd
    );
}