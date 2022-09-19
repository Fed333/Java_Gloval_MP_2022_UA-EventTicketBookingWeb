package org.fed333.ticket.booking.app.model;

import java.io.Serializable;

/**
 * Common interface of all entities with id.
 * @author Roman_Kovalchuk
 * */
public interface Identifiable<ID> extends Serializable {

    ID getId();

    void setId(ID id);

}
