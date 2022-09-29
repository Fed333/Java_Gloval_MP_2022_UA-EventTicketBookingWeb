package org.fed333.ticket.booking.app.repository;

import org.fed333.ticket.booking.app.model.Event;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface EventRepository extends CrudRepository<Event, Long>{

    List<Event> getAllByTitle(String title);

    List<Event> getAllByTitle(String title, int offset, int size);

    List<Event> getAllByDate(Date day);

    List<Event> getAllByDay(Date day);

    List<Event> getAllByDay(Date day, int offset, int size);

}
