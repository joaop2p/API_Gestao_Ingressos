package com.example.demo.services;

import com.example.demo.models.dto.forms.EventsFormDTO;
import com.example.demo.models.dto.absolute.EventDTO;
import com.example.demo.models.entities.Event;
import com.example.demo.models.entities.Local;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.LocalRepository;
import com.example.demo.utils.exceptions.Conflict;
import com.example.demo.utils.exceptions.NotFound;
import com.example.demo.utils.exceptions.ValueError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventsService {
    final EventRepository eventRepository;
    final LocalRepository localRepository;

    private boolean checkEventConflict(Long localId, LocalDateTime start, LocalDateTime end) {
        return eventRepository.existsConflict(localId, start, end);
    }

    private boolean checkEventConflictForUpdate(Long localId, LocalDateTime start, LocalDateTime end) {
        return eventRepository.existsConflictForUpdate(localId, start, end);
    }

    private void checkDates(LocalDateTime start, LocalDateTime end) {
        if (start.isBefore(LocalDateTime.now())) {
            throw new ValueError("Event start time must be in the future");
        } else if (start.isAfter(end)) {
            throw new ValueError("Event start time must be before end time");
        }
    }

    @Transactional
    public Event createEvent(EventsFormDTO event) {
        checkDates(event.start(), event.end());
        Local location = localRepository.findById(event.localId())
                .orElseThrow(() -> new NotFound("Location not found"));
        if(checkEventConflict(event.localId(), event.start(), event.end())) {
            throw new Conflict("Event conflict detected");
        }
        Event entity = new Event();
        entity.setName(event.name());
        entity.setDescription(event.description());
        entity.setStart(event.start());
        entity.setEnd(event.end());
        entity.setLocation(location);
        entity.setPrice(event.price());
        return eventRepository.save(entity);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFound("Event not found with id: " + id));
    }
    @Transactional
    public EventDTO update(EventsFormDTO event, Long id) {
        checkDates(event.start(), event.end());
        Event existingEvent = getEventById(id);
        if (eventRepository.existsConflictForUpdate(existingEvent.getLocation().getId(),event.start(),event.end())) {
            throw new Conflict("Event conflict detected");
        }
        if (existingEvent.getStart().equals(event.start()) && existingEvent.getEnd().equals(event.end()))
            throw new ValueError("Event start or end times must be different from the existing event");
        existingEvent.setName(event.name());
        existingEvent.setDescription(event.description());
        existingEvent.setStart(event.start());
        existingEvent.setEnd(event.end());
        eventRepository.save(existingEvent);
        return existingEvent.toDTO();
    }

    public void deleteEvent(Long id) {
        Event existingEvent = getEventById(id);
        eventRepository.delete(existingEvent);
    }
}
