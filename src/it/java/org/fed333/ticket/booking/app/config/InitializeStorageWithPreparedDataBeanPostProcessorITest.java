package org.fed333.ticket.booking.app.config;


import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.UserAccount;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.fed333.ticket.booking.app.repository.UserAccountRepository;
import org.fed333.ticket.booking.app.repository.UserRepository;
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

import java.util.Comparator;
import java.util.List;

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

    @Autowired
    private UserAccountRepository accountRepository;

    private TestStorageUtil testStorage;

    private EventEqualityComparator eventComparator;

    private UserEqualityComparator userComparator;

    private TicketEqualityComparator ticketComparator;

    @Before
    public void setUp() {
        testStorage = new TestStorageUtil();
        eventComparator = new EventEqualityComparator();
        ticketComparator = new TicketEqualityComparator();
        userComparator = new UserEqualityComparator();
    }

    @Test
    public void eventRepository_shouldBePreInitializedWithJSON() {
        assertThat(eventRepository.findAll())
                .usingComparatorForType(eventComparator, Event.class)
                .isEqualTo(testStorage.getTestEvents().values());
    }

    @Test
    public void userRepository_shouldBePreInitializedWithJSON() {
        List<User> all = userRepository.findAll();
        all.sort(Comparator.comparing(User::getId));
        assertThat(all)
                .usingComparatorForType(userComparator, User.class)
                .isEqualTo(testStorage.getTestUsers().values());

    }
    @Test
    public void ticketRepository_shouldBePreInitializedWithJSON() {
        assertThat(ticketRepository.findAll())
                .usingComparatorForType(ticketComparator, Ticket.class)
                .isEqualTo(testStorage.getTestTickets().values());
    }

}