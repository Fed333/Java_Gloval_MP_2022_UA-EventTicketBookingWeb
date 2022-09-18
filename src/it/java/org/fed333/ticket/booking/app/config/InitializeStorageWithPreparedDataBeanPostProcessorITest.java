package org.fed333.ticket.booking.app.config;


import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.repository.impl.component.StorageData;
import org.fed333.ticket.booking.app.util.TestStorageUtil;
import org.fed333.ticket.booking.app.util.comparator.EventEqualityComparator;
import org.fed333.ticket.booking.app.util.comparator.TicketEqualityComparator;
import org.fed333.ticket.booking.app.util.comparator.UserEqualityComparator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/services.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class InitializeStorageWithPreparedDataBeanPostProcessorITest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    private TestStorageUtil testStorage;

    private EventEqualityComparator eventComparator;

    private UserEqualityComparator userComparator;

    private TicketEqualityComparator ticketComparator;

    @Before
    public void setUp() {
        testStorage = new TestStorageUtil();
        eventComparator = new EventEqualityComparator();
    }

    @Test
    public void eventRepository_shouldBePreInitializedWithJSON() {
        assertThat(eventRepository.getAll())
                .usingComparatorForType(eventComparator, Event.class)
                .isEqualTo(testStorage.getTestEvents().values());
    }

    @Test
    public void userRepository_shouldBePreInitializedWithJSON() {
        assertThat(userRepository.getAll())
                .usingComparatorForType(userComparator, User.class)
                .isEqualTo(testStorage.getTestUsers().values());

    }
    @Test
    public void ticketRepository_shouldBePreInitializedWithJSON() {
        assertThat(ticketRepository.getAll())
                .usingComparatorForType(ticketComparator, Ticket.class)
                .isEqualTo(testStorage.getTestTickets().values());
    }

}