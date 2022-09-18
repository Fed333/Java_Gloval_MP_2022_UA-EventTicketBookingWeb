package org.fed333.ticket.booking.app.util.comparator;

import org.fed333.ticket.booking.app.model.Event;

import java.util.Comparator;

public class EventEqualityComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        return Comparator.comparing(Event::getId)
                .thenComparing(Event::getTitle)
                .thenComparing(Event::getDate).compare(o1, o2);
    }
}
