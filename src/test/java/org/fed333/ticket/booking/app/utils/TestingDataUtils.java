package org.fed333.ticket.booking.app.utils;

import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.impl.EventImpl;
import org.fed333.ticket.booking.app.model.impl.TicketImpl;
import org.fed333.ticket.booking.app.model.impl.UserImpl;

import java.util.Date;

import static org.fed333.ticket.booking.app.utils.DateUtils.parseDate;

public class TestingDataUtils {

    public static final Date TEST_DATE = parseDate("08-09-2022");

    public static Event createTestEvent(Long id) {
        return EventImpl.builder()
                .id(id)
                .title("Event" + id)
                .date(TEST_DATE).build();
    }

    public static Ticket createTestTicket(Long id) {
        return TicketImpl.builder()
                .userId(id)
                .eventId(id)
                .category(Ticket.Category.STANDARD)
                .place((int)(long)id).build();
    }

    public static User createTestUser(Long id) {
        return UserImpl.builder()
                .id(id)
                .name("User" + id)
                .email("user" + id + "@mail.com").build();
    }

    public static User createTestUserWithName(Long id, String name) {
        return UserImpl.builder()
                .id(id)
                .name(name)
                .email("user" + id + "@mail.com").build();
    }
}
