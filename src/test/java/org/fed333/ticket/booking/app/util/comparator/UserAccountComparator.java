package org.fed333.ticket.booking.app.util.comparator;

import org.fed333.ticket.booking.app.model.entity.UserAccount;

import java.util.Comparator;

public class UserAccountComparator implements Comparator<UserAccount> {

    @Override
    public int compare(UserAccount o1, UserAccount o2) {
        return Comparator.comparing(UserAccount::getId)
                .thenComparing(UserAccount::getMoney)
                .compare(o1,o2);
    }
}
