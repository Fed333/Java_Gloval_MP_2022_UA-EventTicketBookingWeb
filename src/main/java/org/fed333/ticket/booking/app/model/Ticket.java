package org.fed333.ticket.booking.app.model;

/**
 * Interface which represents Ticket entity.
 * @author Roman_Kovalchuk
 */
public interface Ticket extends Identifiable<Long>{
    enum Category {STANDARD, PREMIUM, BAR}

    /**
     * Ticket Id. UNIQUE.
     * @return Ticket Id.
     */
    Long getId();

    void setId(Long id);

    Long getEventId();

    void setEventId(Long eventId);

    Long getUserId();

    void setUserId(Long userId);

    Category getCategory();

    void setCategory(Category category);

    int getPlace();

    void setPlace(int place);

    boolean isCancelled();

    void setCancelled(boolean isCancelled);

}
