package org.fed333.ticket.booking.app.caching;


import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.service.EventService;
import org.fed333.ticket.booking.app.service.TicketService;
import org.fed333.ticket.booking.app.service.UserService;
import org.fed333.ticket.booking.app.utils.TestingDataUtils;
import org.hibernate.Session;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/services.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class EhCacheHibernateITest {

    private Statistics statistics;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Before
    public void setUp() {

        Session session = entityManager.unwrap(Session.class);
        statistics = session.getSessionFactory().getStatistics();
        statistics.clear();
    }

    @After
    public void tearDown() {
        statistics.clear();
    }

    @Test
    public void test_eventCaching() {
        Event testEvent = TestingDataUtils.createTestEvent(null);
        eventService.createEvent(testEvent);
        Long eventId = testEvent.getId();
        int expectedHitCount = 9;
        int expectedMissCount = 1;

        for (int i = 0; i < expectedHitCount + expectedMissCount; i++) {
            eventService.getEventById(eventId);
        }

        EntityStatistics eventStatistics = statistics.getEntityStatistics(Event.class.getCanonicalName());
        long actualHitCount = eventStatistics.getCacheHitCount();
        long actualMissCount = eventStatistics.getCacheMissCount();
        assertThat(actualHitCount).isEqualTo(expectedHitCount);
        assertThat(actualMissCount).isEqualTo(expectedMissCount);
    }

    @Test
    public void test_userCaching() {
        User testUser = TestingDataUtils.createTestUser(null);
        userService.createUser(testUser);
        Long eventId = testUser.getId();
        int expectedHitCount = 9;
        int expectedMissCount = 1;

        for (int i = 0; i < expectedHitCount + expectedMissCount; i++) {
            userService.getUserById(eventId);
        }

        EntityStatistics userStatistics = statistics.getEntityStatistics(User.class.getCanonicalName());
        long actualHitCount = userStatistics.getCacheHitCount();
        long actualMissCount = userStatistics.getCacheMissCount();

        assertThat(actualHitCount).isEqualTo(expectedHitCount);
        assertThat(actualMissCount).isEqualTo(expectedMissCount);
    }

    @Test
    public void test_ticketCaching() {
        Event testEvent = TestingDataUtils.createTestEvent(null);
        User testUser = TestingDataUtils.createTestUserWithName(null, "testingCacheUser");

        eventService.createEvent(testEvent);
        userService.createUser(testUser);

        Ticket bookedTicket = ticketService.bookTicket(testUser.getId(), testEvent.getId(), 1, Ticket.Category.STANDARD);

        int expectedHitCount = 9;
        int expectedMissCount = 1;

        for (int i = 0; i < expectedHitCount + expectedMissCount; i++) {
           ticketService.getById(bookedTicket.getId());
        }

        EntityStatistics ticketStatistics = statistics.getEntityStatistics(Ticket.class.getCanonicalName());
        long actualHitCount = ticketStatistics.getCacheHitCount();
        long actualMissCount = ticketStatistics.getCacheMissCount();

        assertThat(actualHitCount).isEqualTo(expectedHitCount);
        assertThat(actualMissCount).isEqualTo(expectedMissCount);
    }

}
