package org.fed333.ticket.booking.app.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.impl.TicketImpl;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.service.component.SaveEntityValidator;
import org.fed333.ticket.booking.app.service.component.SlicePaginator;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Setter
    private SlicePaginator paginator;

    @Setter
    private SaveEntityValidator<Ticket, Long> saveTicketValidator;

    private void init() {
        saveTicketValidator.setRepository(ticketRepository);
    }

    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("No found user with id " + userId + " to create a ticket.");
        }
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("No found event with id " + eventId + " to create a ticket.");
        }

        Ticket ticket = TicketImpl.builder()
                .userId(userId)
                .eventId(eventId)
                .place(place)
                .category(category).build();

        ticketRepository.save(ticket);
        log.info("Ticket {} has been booked successfully.", ticket);
        return ticket;
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return paginator.paginateList(ticketRepository.getAllByUserId(user.getId()), pageNum, pageSize);
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return paginator.paginateList(ticketRepository.getAllByEventId(event.getId()), pageNum, pageSize);
    }

    public boolean cancelTicket(long ticketId) {
        Ticket ticketToCancel = ticketRepository.getById(ticketId);
        ticketToCancel.setCancelled(true);
        return ticketToCancel.isCancelled();
    }

}
