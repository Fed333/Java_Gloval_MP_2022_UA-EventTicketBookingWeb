package org.fed333.ticket.booking.app.repository.impl;

import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.model.impl.UserImpl;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.repository.impl.AbstractHibernateDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractHibernateDao<User, Long> implements UserRepository {

    @Override
    public List<User> getAllByName(String name) {
        DetachedCriteria detachedCriteria = getDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("name", name));
        return findByCriteria(detachedCriteria);
    }

    @Override
    public List<User> getAllByEmail(String email) {
        DetachedCriteria detachedCriteria = getDetachedCriteria();
        detachedCriteria.add(Restrictions.eq("email", email));
        return findByCriteria(detachedCriteria);
    }
}
