package org.fed333.ticket.booking.app;

import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.facade.BookingFacade;
import org.fed333.ticket.booking.app.facade.impl.BookingFacadeImpl;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.impl.EventImpl;
import org.fed333.ticket.booking.app.model.impl.TicketImpl;
import org.fed333.ticket.booking.app.model.impl.UserImpl;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.service.EventService;
import org.fed333.ticket.booking.app.service.TicketService;
import org.fed333.ticket.booking.app.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
public class SpringApplication {

    public static void main(String[] args) throws ParseException {

        log.info("Start raising ApplicationContext...");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:services.xml");
        log.info("ApplicationContext has been raised.");
        BookingFacade bookingFacade = context.getBean(BookingFacade.class);
        UserImpl user = UserImpl.builder()
                .name("Roman")
                .email("romakovalcuk524@gmail.com").build();
        bookingFacade.createUser(user);
        User userById = bookingFacade.getUserById(user.getId());
        log.info("userById.equals(user) = {}", userById.equals(user));

        Event event = EventImpl.builder()
                .date(new SimpleDateFormat("dd.MM.yyyy").parse("05.09.2022"))
                .title("EPAM Townhall").build();
        bookingFacade.createEvent(event);

        bookingFacade.bookTicket(userById.getId(), event.getId(), 3, Ticket.Category.PREMIUM);

        List<Ticket> bookedUserTickets = bookingFacade.getBookedTickets(user, 10, 1);
        List<Ticket> bookedEventTickets = bookingFacade.getBookedTickets(event, 10, 1);

        bookedUserTickets.forEach(t->log.info("Booked ticket of user {} - {}", user, t));
        bookedEventTickets.forEach(t->log.info("Booked ticket of event {} - {}", event, t));
    }
}
