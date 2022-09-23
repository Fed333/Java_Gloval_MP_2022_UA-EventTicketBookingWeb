package org.fed333.ticket.booking.app.repository;

import org.fed333.ticket.booking.app.model.Ticket;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TicketRepository extends CrudRepository<Ticket, Long>{

    List<Ticket> getAllByEventId(Long eventId);

    List<Ticket> getAllByUserId(Long userId);

    Ticket getByUserIdAndEventId(Long userId, Long eventId);
}
