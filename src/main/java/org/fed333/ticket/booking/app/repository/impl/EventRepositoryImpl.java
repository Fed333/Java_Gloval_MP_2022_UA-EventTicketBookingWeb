package org.fed333.ticket.booking.app.repository.impl;

import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.repository.EventRepository;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class EventRepositoryImpl extends AbstractHibernateDao<Event, Long> {
//    @Override
//    public List<Event> findAllByTitle(String title) {
//        return findAllByTitle(title, -1, -1);
//    }
//
//    @Override
//    public List<Event> findAllByTitle(String title, int offset, int size) {
//        DetachedCriteria detachedCriteria = getDetachedCriteria();
//        detachedCriteria.add(Restrictions.eq("title", title));
//        return findByCriteria(detachedCriteria, offset, size);
//    }
//
//    @Override
//    public List<Event> findAllByDate(Date date) {
//        DetachedCriteria detachedCriteria = getDetachedCriteria();
//        detachedCriteria.add(Restrictions.eq("date", date));
//        return findByCriteria(detachedCriteria);
//    }
//
//    @Override
//    public List<Event> getAllByDay(Date day) {
//        return getAllByDay(day, -1, -1);
//    }
//
//    @Override
//    public List<Event> getAllByDay(Date day, int offset, int size) {
//        DetachedCriteria detachedCriteria = getDetachedCriteria();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(day);
//
//        detachedCriteria.add(Restrictions.sqlRestriction("EXTRACT(YEAR FROM date) = ?", calendar.get(Calendar.YEAR), IntegerType.INSTANCE));
//        detachedCriteria.add(Restrictions.sqlRestriction("EXTRACT(MONTH FROM date) = ?", calendar.get(Calendar.MONTH)+1, IntegerType.INSTANCE));
//        detachedCriteria.add(Restrictions.sqlRestriction("EXTRACT(DAY FROM date) = ?", calendar.get(Calendar.DAY_OF_MONTH), IntegerType.INSTANCE));
//        return findByCriteria(detachedCriteria, offset, size);
//    }

}
