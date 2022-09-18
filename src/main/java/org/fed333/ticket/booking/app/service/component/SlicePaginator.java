package org.fed333.ticket.booking.app.service.component;

import java.util.List;
import java.util.stream.Stream;

/**
 * Bean for paginating purposes.
 * Slices source data according to the params.
 * @author Roman_Kovalchuk
 * */
public class SlicePaginator {


    /**
     * Slices list according given parameters.
     * @param list source data to be sliced
     * @param cursor number of current page (starts from 1)
     * @param size size of each page
     * @return sliced list
     * */
    public <T> List<T> paginateList(List<T> list, int cursor, int size) {
        requiredValidPaginationParams(cursor, size);
        size = Math.min(list.size(), size);
        return list.subList((cursor-1)*size, Math.min((cursor-1)*size+size, list.size()));
    }

    /**
     * Slices stream according given parameters.
     * @param stream source data to be sliced
     * @param cursor number of current page (starts from 1)
     * @param size size of each page
     * @return sliced stream
     * */
    public <T> Stream<T> paginateStream(Stream<T> stream, int cursor, int size) {
        requiredValidPaginationParams(cursor, size);
        return stream.skip((long) (cursor-1)*size).limit(size);
    }

    private void requiredValidPaginationParams(int cursor, int size) {
        if (cursor < 1) {
            throw new RuntimeException("Number of page must starts from 1!");
        }
        if (size < 0) {
            throw new RuntimeException("Size cannot be less than a zero!");
        }
    }

}
