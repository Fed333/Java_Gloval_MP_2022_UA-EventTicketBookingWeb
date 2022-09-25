package org.fed333.ticket.booking.app.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An immutable class, for pagination purposes.
 * @author Roman_Kovalchuk
 * */
@AllArgsConstructor
@Getter
public class PageUtil {

    private int page;

    private int size;

    /**
     * Calculates offset according to the given attributes.<br>
     * Starts page from 1.
     * @return offset of records
     * */
    public int getOffset() {
        return (page-1)*size;
    }

}
