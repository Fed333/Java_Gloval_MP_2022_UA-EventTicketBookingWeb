package org.fed333.ticket.booking.app.utils;

import org.fed333.ticket.booking.app.model.*;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;

import java.util.Date;

import static org.fed333.ticket.booking.app.utils.DateUtils.parseDate;

public class TestingDataUtils {

    public static final Date TEST_DATE = parseDate("08-09-2022");

    public static Event createTestEvent(Long id) {
        return Event.builder()
                .id(id)
                .title("Event" + id)
                .date(TEST_DATE)
                .ticketPrice(50d).build();
    }

    public static Ticket createTestTicket(Long id) {
        return Ticket.builder()
                .user(User.builder().id(id).build())
                .event(Event.builder().id(id).build())
                .category(Ticket.Category.STANDARD)
                .place((int)(long)id).build();
    }

    public static User createTestUser(Long id) {
        return User.builder()
                .id(id)
                .name("User" + id)
                .email("user" + id + "@mail.com")
                .account(UserAccount.builder().id(id).money(5000d).build()).build();
    }

    public static User createTestUserWithName(Long id, String name) {
        return User.builder()
                .id(id)
                .name(name)
                .email("user" + id + "@mail.com")
                .account(UserAccount.builder().id(id).money(5000d).build()).build();
    }
}
