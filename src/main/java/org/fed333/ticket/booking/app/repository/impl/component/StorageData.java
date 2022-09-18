package org.fed333.ticket.booking.app.repository.impl.component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.repository.impl.EventRepositoryImpl;
import org.fed333.ticket.booking.app.repository.impl.TicketRepositoryImpl;
import org.fed333.ticket.booking.app.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

/**
 * The bean, to initialize implementations of repository classes
 * on the startup of {@link ApplicationContext}
 * using {@link BeanPostProcessor} features.<br>
 * @author Roman_Kovalchuk
 * */
@Slf4j
@ToString
@Getter @Setter
public class StorageData {

    private EventRepositoryImpl eventRepository;

    private UserRepositoryImpl userRepository;

    private TicketRepositoryImpl ticketRepository;

    private void init() {
        log.info("StorageData init() method invoked!");

        log.info("Total events: {}", eventRepository.getAll());
        log.info("Total users: {}", userRepository.getAll());
        log.info("Total repositories: {}", ticketRepository.getAll());
    }

}
