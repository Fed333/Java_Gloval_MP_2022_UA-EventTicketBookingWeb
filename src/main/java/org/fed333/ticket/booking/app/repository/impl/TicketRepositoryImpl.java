package org.fed333.ticket.booking.app.repository.impl;

import lombok.Getter;
import lombok.Setter;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.fed333.ticket.booking.app.repository.impl.component.LongIdGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class TicketRepositoryImpl extends AbstractCrudDao<Ticket, Long> implements TicketRepository {

    @Getter @Setter
    private LongIdGenerator idGenerator;

    @Override
    protected Long nextId() {
        return idGenerator.generateNextId();
    }

    @Override
    public List<Ticket> getAllByEventId(Long eventId) {
        return getAll().stream().filter(t->t.getEventId().equals(eventId)).collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getAllByUserId(Long userId) {
        return getAll().stream().filter(t->t.getUserId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public Ticket getByUserIdAndEventId(Long userId, Long eventId) {
        return getAll().stream().filter(t->t.getEventId().equals(eventId)).filter(t->t.getUserId().equals(userId)).findFirst().orElse(null);
    }
}