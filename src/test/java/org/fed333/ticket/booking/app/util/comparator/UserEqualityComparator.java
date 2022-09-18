package org.fed333.ticket.booking.app.util.comparator;

import org.fed333.ticket.booking.app.model.User;

import java.util.Comparator;

public class UserEqualityComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return Comparator.comparing(User::getId, Comparator.nullsFirst(Long::compare))
                .thenComparing(User::getName)
                .thenComparing(User::getEmail).compare(o1, o2);
    }
}
