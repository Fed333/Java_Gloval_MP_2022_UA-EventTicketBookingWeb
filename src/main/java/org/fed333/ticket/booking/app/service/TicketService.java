package org.fed333.ticket.booking.app.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.service.component.SaveEntityValidator;
import org.fed333.ticket.booking.app.util.PageUtil;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

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

        Ticket ticket = Ticket.builder()
                .user(User.builder().id(userId).build())
                .event(Event.builder().id(eventId).build())
                .place(place)
                .category(category).build();

        ticketRepository.save(ticket);
        log.info("Ticket {} has been booked successfully.", ticket);
        return ticket;
    }

    public List<Ticket> getBookedTickets(User user, PageUtil page) {
        return ticketRepository.getAllByUserId(user.getId(), page.getOffset(), page.getSize());
    }

    public List<Ticket> getBookedTickets(Event event, PageUtil page) {
        return ticketRepository.getAllByEventId(event.getId(), page.getOffset(), page.getSize());
    }

    public boolean cancelTicket(long ticketId) {
        Ticket ticketToCancel = ticketRepository.getById(ticketId);
        ticketToCancel.setCancelled(true);
        ticketRepository.save(ticketToCancel);
        return ticketToCancel.isCancelled();
    }

}
