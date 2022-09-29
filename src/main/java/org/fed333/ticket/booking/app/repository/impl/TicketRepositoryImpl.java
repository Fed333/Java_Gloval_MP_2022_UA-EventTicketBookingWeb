package org.fed333.ticket.booking.app.repository.impl;

import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.repository.TicketRepository;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class TicketRepositoryImpl extends AbstractHibernateDao<Ticket, Long> implements TicketRepository {
    @Override
    public List<Ticket> getAllByEventId(Long eventId) {
        DetachedCriteria detachedCriteria = getDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("event.id", eventId));
        return (List<Ticket>) detachedCriteria.getExecutableCriteria(getSession()).list();
    }

    @Override
    public List<Ticket> getAllByEventId(Long eventId, int offset, int size) {
        DetachedCriteria detachedCriteria = getDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("event.id", eventId));
        detachedCriteria.addOrder(Order.asc("id"));
        return findByCriteria(detachedCriteria, offset, size);
    }

    @Override
    public List<Ticket> getAllByUserId(Long userId) {
        return getAllByUserId(userId, -1, -1);
    }

    @Override
    public List<Ticket> getAllByUserId(Long userId, int offset, int size) {
        DetachedCriteria detachedCriteria = getDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("user.id", userId));
        return findByCriteria(detachedCriteria, offset, size);

    }

    @Override
    public Ticket getByUserIdAndEventId(Long userId, Long eventId) {
        DetachedCriteria detachedCriteria = getDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("event.id", eventId));
        detachedCriteria.add(Restrictions.eq("user.id", userId));
        return (Ticket) detachedCriteria.getExecutableCriteria(getSession()).uniqueResult();
    }
}
