package org.fed333.ticket.booking.app.repository.impl.component;

import lombok.Setter;

/**
 * Bean for generating unique ids for repository implementation classes.
 * @author Roman_Kovalchuk
 * */
public class LongIdGenerator {

    @Setter
    private Long currId;

    /**
     * Increments the previous id value to get a unique one.
     * */
    public Long generateNextId(){
        return ++currId;
    }

}
