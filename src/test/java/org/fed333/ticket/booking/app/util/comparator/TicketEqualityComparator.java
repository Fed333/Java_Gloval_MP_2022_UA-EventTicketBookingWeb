package org.fed333.ticket.booking.app.util.comparator;

import org.fed333.ticket.booking.app.model.Ticket;

import java.util.Comparator;

public class TicketEqualityComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket o1, Ticket o2) {
        return Comparator.comparing(Ticket::getId, Comparator.nullsFirst(Long::compare))
                .thenComparing(Ticket::getEventId)
                .thenComparing(Ticket::getUserId)
                .thenComparing(Ticket::getPlace)
                .thenComparing(Ticket::getCategory)
                .thenComparing(Ticket::isCancelled).compare(o1,o2);
    }
}
