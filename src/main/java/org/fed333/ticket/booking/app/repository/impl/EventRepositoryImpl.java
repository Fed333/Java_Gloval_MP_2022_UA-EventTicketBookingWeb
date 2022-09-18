package org.fed333.ticket.booking.app.repository.impl;

import lombok.Getter;
import lombok.Setter;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.repository.impl.component.LongIdGenerator;
import org.fed333.ticket.booking.app.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.fed333.ticket.booking.app.utils.DateUtils.extractDayOfYear;
import static org.fed333.ticket.booking.app.utils.DateUtils.extractYear;

public class EventRepositoryImpl extends AbstractCrudDao<Event,Long> implements EventRepository {

    @Getter @Setter
    private LongIdGenerator idGenerator;

    @Override
    protected Long nextId() {
        return idGenerator.generateNextId();
    }

    @Override
    public List<Event> getAllByTitle(String title) {
        return super.getAll().stream().filter(e->e.getTitle().equals(title)).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllByDate(Date date) {
        return super.getAll().stream().filter(e->e.getDate().equals(date)).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllByDay(Date day) {
        return super.getAll().stream().filter(e -> isSameDays(day, e.getDate())).collect(Collectors.toList());
    }

    private boolean isSameDays(Date day1, Date day2) {
        return  extractYear(day1) == extractYear(day2) && extractDayOfYear(day1) == extractDayOfYear(day2);
    }
}
