package org.fed333.ticket.booking.app.util;

import lombok.Getter;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.impl.EventImpl;
import org.fed333.ticket.booking.app.model.impl.TicketImpl;
import org.fed333.ticket.booking.app.model.impl.UserImpl;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.fed333.ticket.booking.app.utils.DateUtils.parseDateTime;

@Getter
public class TestStorageUtil {

    private final Map<Long, Event> testEvents;

    private final Map<Long, User> testUsers;

    private final Map<Long, Ticket> testTickets;


    public TestStorageUtil() {
        testEvents = Stream.of(
                new Object[][]{
                        {1L, new EventImpl(1L, "EPAM Townhall", parseDateTime("2022-09-05 15:30:00"))},
                        {2L, new EventImpl(2L, "VNTU openday", parseDateTime("2022-09-05 11:20:00"))},
                        {3L, new EventImpl(3L, "Kalush charity music concert", parseDateTime("2022-09-06 18:45:00"))},
                        {4L, new EventImpl(4L, "EPAM Townhall", parseDateTime("2022-10-05 15:30:00"))},
                        {5L, new EventImpl(5L, "EPAM Townhall", parseDateTime("2022-11-05 15:30:00"))},
                        {6L, new EventImpl(6L, "Event1 to be deleted", parseDateTime("2022-12-05 15:30:00"))},
                }
        ).collect(toMap(o -> (Long) o[0], o -> (Event) o[1]));

        testUsers = Stream.of(
                new Object[][]{
                        {1L, new UserImpl(1L, "Roman", "kovalchuk.roman03@gmail.com")},
                        {2L, new UserImpl(2L, "Pips", "mpostryk@gmail.com")},
                        {3L, new UserImpl(3L, "Ivan", "builbik@gmail.com")},
                        {4L, new UserImpl(4L, "Kyrylo", "kyrylo@gmail.com")},
                        {5L, new UserImpl(5L, "Ivan", "vanno8782@gmail.com")},
                        {6L, new UserImpl(6L, "Serhiy", "meizum@gmail.com")},
                        {7L, new UserImpl(7L, "Andrii", "riko@gmail.com")},
                        {8L, new UserImpl(8L, "Iryna", "ira@gmail.com")},
                        {9L, new UserImpl(9L, "Tetyana", "taniusha@gmail.com")},
                        {10L, new UserImpl(10L, "Viktoria", "viktoria@gmail.com")},
                        {11L, new UserImpl(11L, "Ivan", "tocker342@gmail.com")},
                        {12L, new UserImpl(12L, "Mykhailo", "mykhailo@gmail.com")},
                        {13L, new UserImpl(13L, "Hlib", "glego@gmail.com")},
                        {14L, new UserImpl(14L, "Pavlo", "pavlo_makushak@gmail.com")},
                        {15L, new UserImpl(15L, "Updated", "updated@gmail.com")},
                        {16L, new UserImpl(16L, "Deleted", "deleted@gmail.com")}
                }
        ).collect(toMap(o -> (Long) o[0], o -> (User) o[1]));
        testTickets = Stream.of(
                new Object[][]{
                    {1L, new TicketImpl(1L,  1L,  1L,  Ticket.Category.PREMIUM,  12,  false)},
                    {2L, new TicketImpl(2L,  1L,  3L,  Ticket.Category.STANDARD,  13,  false)},
                    {3L, new TicketImpl(3L,  1L,  5L,  Ticket.Category.BAR,  2,  true)},
                    {4L, new TicketImpl(4L,  1L,  13L,  Ticket.Category.STANDARD,  10,  true)},
                    {5L, new TicketImpl(5L,  2L,  11L,  Ticket.Category.STANDARD,  1,  false)},
                    {6L, new TicketImpl(6L,  2L,  14L,  Ticket.Category.STANDARD,  2,  false)},
                    {7L, new TicketImpl(7L,  2L,  6L,  Ticket.Category.STANDARD,  3,  true)},
                    {8L, new TicketImpl(8L,  3L,  4L,  Ticket.Category.STANDARD,  132,  false)},
                    {9L, new TicketImpl(9L,  3L,  3L,  Ticket.Category.STANDARD,  133,  false)},
                    {10L, new TicketImpl(10L,  3L,  1L,  Ticket.Category.STANDARD,  134,  false)},
                    {11L, new TicketImpl(11L,  3L,  2L,  Ticket.Category.STANDARD,  135,  false)},
                    {12L, new TicketImpl(12L,  3L,  7L,  Ticket.Category.STANDARD,  136,  false)},
                    {13L, new TicketImpl(13L,  3L,  6L,  Ticket.Category.STANDARD,  137,  false)},
                    {14L, new TicketImpl(14L,  3L,  8L,  Ticket.Category.PREMIUM,  10,  false)},
                    {15L, new TicketImpl(15L,  3L,  9L,  Ticket.Category.PREMIUM,  11,  false)},
                    {16L, new TicketImpl(16L,  3L,  10L,  Ticket.Category.PREMIUM,  12,  false)},
                    {17L, new TicketImpl(17L,  3L,  13L,  Ticket.Category.STANDARD,  138,  false)},
                    {18L, new TicketImpl(18L,  3L,  5L,  Ticket.Category.STANDARD,  139,  false)},
                    {19L, new TicketImpl(19L,  3L,  12L,  Ticket.Category.STANDARD,  140,  false)},
                    {20L, new TicketImpl(20L,  3L,  14L,  Ticket.Category.STANDARD,  141,  false)}
                }
        ).collect(toMap(o -> (Long) o[0], o -> (Ticket) o[1]));

    }

    public int getEventsNumber() {
        return testEvents.size();
    }

    public int getUsersNumber() {
        return testUsers.size();
    }

    public int getTicketsNumber() {
        return testTickets.size();
    }

}
