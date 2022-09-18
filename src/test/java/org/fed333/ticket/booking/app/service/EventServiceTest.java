package org.fed333.ticket.booking.app.service;

import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.impl.EventImpl;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.fed333.ticket.booking.app.service.component.SaveEntityValidator;
import org.fed333.ticket.booking.app.service.component.SlicePaginator;
import org.fed333.ticket.booking.app.util.comparator.EventEqualityComparator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fed333.ticket.booking.app.utils.DateUtils.parseDate;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    private EventRepository mockedRepository;

    @Mock
    private SaveEntityValidator<Event,Long> eventValidator;

    @InjectMocks
    private EventService eventService;

    private Event testEvent;

    private Date testDate;

    private EventEqualityComparator eventEqualityComparator;

    @Before
    public void setUp() {
        eventService.setSaveEventValidator(eventValidator);
        eventService.setPaginator(new SlicePaginator());
        testEvent = EventImpl.builder()
                .id(1L)
                .title("test event")
                .date(new Date()).build();
        eventEqualityComparator = new EventEqualityComparator();
        testDate = parseDate("10-09-2022");
    }

    @Test
    public void getEventById_shouldReturnEvent() {
        Long id = testEvent.getId();
        when(mockedRepository.getById(id)).thenReturn(testEvent);

        assertThat(eventService.getEventById(id)).isEqualTo(testEvent);
    }

    @Test
    public void getEventsByTitle_shouldReturnSubList() {
        String title = testEvent.getTitle();
        int cursor = 2, size = 4;
        List<Event> testList = Stream.iterate(1L, s -> s + 1).limit(10).map(this::createTestEvent).collect(Collectors.toList());
        List<Event> expectedList = Stream.iterate(5L, s -> s + 1).limit(4).map(this::createTestEvent).collect(Collectors.toList());
        when(mockedRepository.getAllByTitle(title)).thenReturn(testList);

        assertThat(new ArrayList<>(eventService.getEventsByTitle(title, size, cursor))).usingComparatorForType(eventEqualityComparator, Event.class).isEqualTo(expectedList);
    }

    @Test
    public void getEventsForDay_shouldReturnSubList() {
        Date date = parseDate("17-09-2022");
        int cursor = 2, size = 4;
        List<Event> testList = Stream.iterate(1L, s -> s + 1).limit(10).map(this::createTestEvent).collect(Collectors.toList());
        List<Event> expectedList = Stream.iterate(5L, s -> s + 1).limit(4).map(this::createTestEvent).collect(Collectors.toList());
        when(mockedRepository.getAllByDay(date)).thenReturn(testList);

        assertThat(new ArrayList<>(eventService.getEventsForDay(date, size, cursor))).usingComparatorForType(eventEqualityComparator, Event.class).isEqualTo(expectedList);
    }

    @Test
    public void createEvent_shouldInvokeRepository() {
        eventService.createEvent(testEvent);

        verify(mockedRepository).save(testEvent);
    }

    @Test
    public void createEvent_shouldInvokeValidator() {
        eventService.createEvent(testEvent);

        verify(eventValidator).validateCreate(testEvent);
    }

    @Test
    public void updateEvent_shouldInvokeRepository() {
        eventService.updateEvent(testEvent);

        verify(mockedRepository).save(testEvent);
    }

    @Test
    public void updateEvent_shouldInvokeValidator() {
        eventService.updateEvent(testEvent);

        verify(eventValidator).validateUpdate(testEvent);
    }

    @Test
    public void deleteEvent_shouldInvokeRepository() {
        eventService.deleteEvent(testEvent.getId());

        verify(mockedRepository).remove(testEvent.getId());
    }

    private Event createTestEvent(Long id) {
        return EventImpl.builder()
                .id(id)
                .title("Event" + id)
                .date(testDate).build();
    }

}