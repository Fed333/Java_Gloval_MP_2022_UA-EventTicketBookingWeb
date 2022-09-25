package org.fed333.ticket.booking.app.util;

import lombok.Getter;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.UserAccount;

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
                        {1L, new Event(1L, "EPAM Townhall", parseDateTime("2022-09-05 15:30:00"), null, 20 )},
                        {2L, new Event(2L, "VNTU openday", parseDateTime("2022-09-05 11:20:00"), null, 20)},
                        {3L, new Event(3L, "Kalush charity music concert", parseDateTime("2022-09-06 18:45:00"), null, 3600)},
                        {4L, new Event(4L, "EPAM Townhall", parseDateTime("2022-10-05 15:30:00"), null, 20)},
                        {5L, new Event(5L, "EPAM Townhall", parseDateTime("2022-11-05 15:30:00"), null, 20)},
                        {6L, new Event(6L, "Event1 to be deleted", parseDateTime("2022-12-05 15:30:00"), null, 20)},
                        {7L, new Event(7L, "Jazz concert", parseDateTime("2022-09-25 19:30:00"), null, 20)},
                        {8L, new Event(8L, "Speaking club", parseDateTime("2022-09-25 15:30:00"), null, 20)},
                }
        ).collect(toMap(o -> (Long) o[0], o -> (Event) o[1]));

        testUsers = Stream.of(
                new Object[][]{
                        {1L, new User(1L, "Roman", "kovalchuk.roman03@gmail.com", UserAccount.builder().id(1L).money(6500d).build())},
                        {2L, new User(2L, "Pips", "mpostryk@gmail.com", UserAccount.builder().id(2L).money(4500d).build())},
                        {3L, new User(3L, "Ivan", "builbik@gmail.com", UserAccount.builder().id(3L).money(7500d).build())},
                        {4L, new User(4L, "Kyrylo", "kyrylo@gmail.com", null)},
                        {5L, new User(5L, "Ivan", "vanno8782@gmail.com", null)},
                        {6L, new User(6L, "Serhiy", "meizum@gmail.com", null)},
                        {7L, new User(7L, "Andrii", "riko@gmail.com", null)},
                        {8L, new User(8L, "Iryna", "ira@gmail.com", null)},
                        {9L, new User(9L, "Tetyana", "taniusha@gmail.com", null)},
                        {10L, new User(10L, "Viktoria", "viktoria@gmail.com", null)},
                        {11L, new User(11L, "Ivan", "tocker342@gmail.com", null)},
                        {12L, new User(12L, "Mykhailo", "mykhailo@gmail.com", null)},
                        {13L, new User(13L, "Hlib", "glego@gmail.com", null)},
                        {14L, new User(14L, "Pavlo", "pavlo_makushak@gmail.com", null)},
                        {15L, new User(15L, "Updated", "updated@gmail.com", null)},
                        {16L, new User(16L, "Deleted", "deleted@gmail.com", null)}
                }
        ).collect(toMap(o -> (Long) o[0], o -> (User) o[1]));
        testTickets = Stream.of(
                new Object[][]{
                    {1L, new Ticket(1L,  Event.builder().id(1L).build(),   User.builder().id(1L).build(),   Ticket.Category.PREMIUM,  12,  false)},
                    {2L, new Ticket(2L,  Event.builder().id(1L).build(),   User.builder().id(3L).build(),   Ticket.Category.STANDARD,  13,  false)},
                    {3L, new Ticket(3L,  Event.builder().id(1L).build(),   User.builder().id(5L).build(),   Ticket.Category.BAR,  2,  true)},
                    {4L, new Ticket(4L,  Event.builder().id(1L).build(),   User.builder().id(13L).build(),   Ticket.Category.STANDARD,  10,  true)},
                    {5L, new Ticket(5L,  Event.builder().id(2L).build(),   User.builder().id(11L).build(),   Ticket.Category.STANDARD,  1,  false)},
                    {6L, new Ticket(6L,  Event.builder().id(2L).build(),   User.builder().id(14L).build(),   Ticket.Category.STANDARD,  2,  false)},
                    {7L, new Ticket(7L,  Event.builder().id(2L).build(),   User.builder().id(6L).build(),   Ticket.Category.STANDARD,  3,  true)},
                    {8L, new Ticket(8L,  Event.builder().id(3L).build(),   User.builder().id(4L).build(),   Ticket.Category.STANDARD,  132,  false)},
                    {9L, new Ticket(9L,  Event.builder().id(3L).build(),   User.builder().id(3L).build(),   Ticket.Category.STANDARD,  133,  false)},
                    {10L, new Ticket(10L,  Event.builder().id(3L).build(),   User.builder().id(1L).build(),   Ticket.Category.STANDARD,  134,  false)},
                    {11L, new Ticket(11L,  Event.builder().id(3L).build(),   User.builder().id(2L).build(),   Ticket.Category.STANDARD,  135,  false)},
                    {12L, new Ticket(12L,  Event.builder().id(3L).build(),   User.builder().id(7L).build(),   Ticket.Category.STANDARD,  136,  false)},
                    {13L, new Ticket(13L,  Event.builder().id(3L).build(),   User.builder().id(6L).build(),   Ticket.Category.STANDARD,  137,  false)},
                    {14L, new Ticket(14L,  Event.builder().id(3L).build(),   User.builder().id(8L).build(),   Ticket.Category.PREMIUM,  10,  false)},
                    {15L, new Ticket(15L,  Event.builder().id(3L).build(),   User.builder().id(9L).build(),   Ticket.Category.PREMIUM,  11,  false)},
                    {16L, new Ticket(16L,  Event.builder().id(3L).build(),   User.builder().id(10L).build(),   Ticket.Category.PREMIUM,  12,  false)},
                    {17L, new Ticket(17L,  Event.builder().id(3L).build(),   User.builder().id(13L).build(),   Ticket.Category.STANDARD,  138,  false)},
                    {18L, new Ticket(18L,  Event.builder().id(3L).build(),   User.builder().id(5L).build(),   Ticket.Category.STANDARD,  139,  false)},
                    {19L, new Ticket(19L,  Event.builder().id(3L).build(),   User.builder().id(12L).build(),   Ticket.Category.STANDARD,  140,  false)},
                    {20L, new Ticket(20L,  Event.builder().id(3L).build(),   User.builder().id(14L).build(),   Ticket.Category.STANDARD,  141,  false)}
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
