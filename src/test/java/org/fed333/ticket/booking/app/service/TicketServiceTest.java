package org.fed333.ticket.booking.app.service;

import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.service.component.SaveEntityValidator;
import org.fed333.ticket.booking.app.util.PageUtil;
import org.fed333.ticket.booking.app.util.comparator.TicketEqualityComparator;
import org.fed333.ticket.booking.app.utils.TestingDataUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fed333.ticket.booking.app.utils.TestingDataUtils.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository mockedTicketRepository;

    @Mock
    private UserRepository mockedUserRepository;

    @Mock
    private EventRepository mockedEventRepository;

    @Mock
    private SaveEntityValidator<Ticket, Long> mockedValidator;

    @InjectMocks
    private TicketService ticketService;

    private TicketEqualityComparator ticketComparator;

    private User testUser;

    private Event testEvent;

    @Before
    public void setUp() {
        ticketService.setSaveTicketValidator(mockedValidator);
        ticketComparator = new TicketEqualityComparator();
        testUser = createTestUser(1L);
        testEvent = createTestEvent(1L);
    }

    @Test(expected = RuntimeException.class)
    public void bookTicket_ifUserNotExistsShouldThrowException() {
        long userId = 1L, eventId = 2L;
        when(mockedUserRepository.existsById(userId)).thenReturn(false);

        ticketService.bookTicket(userId, eventId, 3, Ticket.Category.STANDARD);
    }

    @Test(expected = RuntimeException.class)
    public void bookTicket_ifEventNotExistsShouldThrowException() {
        long userId = 1L, eventId = 2L;
        when(mockedUserRepository.existsById(userId)).thenReturn(true);
        when(mockedEventRepository.existsById(eventId)).thenReturn(false);

        ticketService.bookTicket(userId, eventId, 3, Ticket.Category.STANDARD);
    }

    @Test
    public void bookTicket_ifOkShouldInvokeRepository() {
        long userId = 1L, eventId = 2L;
        int place = 3;
        when(mockedUserRepository.existsById(userId)).thenReturn(true);
        when(mockedEventRepository.existsById(eventId)).thenReturn(true);
        Ticket expectedTicket = Ticket.builder()
                .user(User.builder().id(userId).build())
                .event(Event.builder().id(eventId).build())
                .place(place)
                .category(Ticket.Category.STANDARD).build();
        ArgumentCaptor<Ticket> ticketCaptor = ArgumentCaptor.forClass(Ticket.class);

        ticketService.bookTicket(userId, eventId, place, Ticket.Category.STANDARD);

        verify(mockedTicketRepository).save(ticketCaptor.capture());
        assertThat(ticketCaptor.getValue()).usingComparator(ticketComparator).isEqualTo(expectedTicket);
    }

    @Test
    public void getBookedTicketsByUser_shouldReturnTickets() {
        int cursor = 1, size = 5;
        PageUtil page = new PageUtil(cursor, size);
        List<Ticket> expectedTickets = Stream.iterate(1L, i -> i + 1).limit(5).map(TestingDataUtils::createTestTicket).collect(Collectors.toList());
        when(mockedTicketRepository.getAllByUserId(testUser.getId(), page.getOffset(), page.getSize())).thenReturn(expectedTickets);

        List<Ticket> actualTickets = ticketService.getBookedTickets(testUser, page);

        assertThat(actualTickets).usingElementComparator(ticketComparator).isEqualTo(expectedTickets);
    }

    @Test
    public void getBookedTicketsByUser_shouldInvokeRepository() {
        int cursor = 1, size = 5;
        PageUtil page = new PageUtil(cursor, size);
        List<Ticket> expectedTickets = Stream.iterate(1L, i -> i + 1).limit(5).map(TestingDataUtils::createTestTicket).collect(Collectors.toList());

        ticketService.getBookedTickets(testUser, page);

        verify(mockedTicketRepository).getAllByUserId(testUser.getId(), page.getOffset(), page.getSize());
    }

    @Test
    public void getBookedTicketsByEvent_shouldReturnTickets() {
        int cursor = 1, size = 5;
        PageUtil page = new PageUtil(cursor, size);
        List<Ticket> expectedTickets = Stream.iterate(1L, i -> i + 1).limit(5).map(TestingDataUtils::createTestTicket).collect(Collectors.toList());
        when(mockedTicketRepository.getAllByEventId(testEvent.getId(), page.getOffset(), page.getSize())).thenReturn(expectedTickets);

        List<Ticket> actualTickets = ticketService.getBookedTickets(testEvent, page);

        assertThat(actualTickets).usingElementComparator(ticketComparator).isEqualTo(expectedTickets);
    }

    @Test
    public void getBookedTicketsByEvent_shouldInvokeRepository() {
        int cursor = 1, size = 5;
        PageUtil page = new PageUtil(cursor, size);
        List<Ticket> expectedTickets = Stream.iterate(1L, i -> i + 1).limit(5).map(TestingDataUtils::createTestTicket).collect(Collectors.toList());
        when(mockedTicketRepository.getAllByEventId(testEvent.getId(), page.getOffset(), page.getSize())).thenReturn(expectedTickets);

        ticketService.getBookedTickets(testEvent, page);

        verify(mockedTicketRepository).getAllByEventId(testEvent.getId(), page.getOffset(), page.getSize());
    }

    @Test
    public void cancelTicket_shouldCancel() {
        long ticketId = 1L;
        Ticket testTicket = createTestTicket(ticketId);
        when(mockedTicketRepository.getById(ticketId)).thenReturn(testTicket);

        ticketService.cancelTicket(ticketId);

        assertThat(testTicket.isCancelled()).isTrue();
    }
}