package org.fed333.ticket.booking.app.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.service.component.SaveEntityValidator;
import org.fed333.ticket.booking.app.util.PageUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Setter
    private SaveEntityValidator<Event, Long> saveEventValidator;

    private void init() {
        saveEventValidator.setRepository(eventRepository);
    }

    public Event getEventById(Long id) {
        return eventRepository.getById(id);
    }

    public List<Event> getEventsByTitle(String title, PageUtil page) {
        return eventRepository.getAllByTitle(title, page.getOffset(), page.getSize());
    }

    public List<Event> getEventsForDay(Date day, PageUtil page) {
        return eventRepository.getAllByDay(day, page.getOffset(), page.getSize());
    }

    public Event createEvent(Event event) {
        saveEventValidator.validateCreate(event);
        Event saved = eventRepository.save(event);
        log.info("Event {} has been created successfully.", saved);
        return saved;
    }

    public Event updateEvent(Event event) {
        saveEventValidator.validateUpdate(event);
        Event saved = eventRepository.save(event);
        log.info("Event {} has been updated successfully.", saved);
        return saved;
    }

    public boolean deleteEvent(long eventId) {
        Event removed = eventRepository.remove(eventId);
        if (Objects.nonNull(removed)) {
            log.info("Event {} was deleted successfully.", removed);
            return true;
        }
        return false;
    }

}
