package org.fed333.ticket.booking.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.mapper.EventMapper;
import org.fed333.ticket.booking.app.model.dto.EventDto;
import org.fed333.ticket.booking.app.service.EventService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Transactional
@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final EventMapper eventMapper;

    @GetMapping(path = "/{eventId}")
    public EventDto get(@PathVariable(name = "eventId") Long eventId) {
        return eventMapper.toDto(eventService.getEventById(eventId));
    }

    @GetMapping
    public List<EventDto> getAll() {
        return eventMapper.toDtos(eventService.getEvents());
    }

}
