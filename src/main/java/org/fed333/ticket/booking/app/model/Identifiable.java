package org.fed333.ticket.booking.app.model;

/**
 * Common interface of all entities with id.
 * @author Roman_Kovalchuk
 * */
public interface Identifiable<ID> {

    ID getId();

    void setId(ID id);

}
