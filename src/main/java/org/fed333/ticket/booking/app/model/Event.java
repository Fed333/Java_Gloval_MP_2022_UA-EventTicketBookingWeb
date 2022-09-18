package org.fed333.ticket.booking.app.model;

import java.util.Date;

/**
 * Interface which represent Event entity.
 * @author Roman_Kovalchuk
 */
public interface Event extends Identifiable<Long>{
    /**
     * Event id. UNIQUE.
     * @return Event Id
     */
    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    Date getDate();

    void setDate(Date date);


}
