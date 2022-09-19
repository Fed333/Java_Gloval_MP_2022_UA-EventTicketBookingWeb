package org.fed333.ticket.booking.app.repository.impl;

import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class EventRepositoryImpl extends AbstractHibernateDao<Event, Long> implements EventRepository {
    @Override
    public List<Event> getAllByTitle(String title) {
        DetachedCriteria detachedCriteria = getDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("title", title));
        return findByCriteria(detachedCriteria);
    }

    @Override
    public List<Event> getAllByDate(Date date) {
        DetachedCriteria detachedCriteria = getDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("date", date));
        return findByCriteria(detachedCriteria);
    }

    @Override
    public List<Event> getAllByDay(Date day) {
        //TODO make it filter properly
        Query<Event> query = getSession().createQuery("SELECT E FROM Event E", Event.class);
        return query.list();
    }

}
