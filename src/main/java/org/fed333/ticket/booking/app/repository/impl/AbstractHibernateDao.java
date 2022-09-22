package org.fed333.ticket.booking.app.repository.impl;

import lombok.SneakyThrows;
import org.fed333.ticket.booking.app.model.Identifiable;
import org.fed333.ticket.booking.app.repository.CrudRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

@Transactional
public abstract class AbstractHibernateDao<E extends Identifiable<ID>, ID extends Serializable> implements CrudRepository<E, ID> {

    private final Class<E> entityClass = getEntityClass();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean existsById(ID id) {
        return Objects.nonNull(getSession().get(entityClass, id));
    }

    @Override
    public E getById(ID id) {
        return getSession().get(entityClass, id);
    }

    @Override
    public List<E> getAll() {
        return findByCriteria(getDetachedCriteria());
    }

    @Override
    public E save(E e) {
        Objects.requireNonNull(e);
        getSession().save(e);
        getSession().flush();
        return e;
    }

    @Override
    public E remove(E e) {
        Objects.requireNonNull(e);
        getSession().delete(e);
        getSession().flush();
        return e;
    }

    @Override
    @SneakyThrows
    public E remove(ID id) {
        E e = getEntityClass().getConstructor().newInstance();
        e.setId(id);
        return remove(e);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Utility method for creating {@link DetachedCriteria} instance.
     * @return {@link DetachedCriteria} of entity class
     * */
    protected DetachedCriteria getDetachedCriteria() {
        return DetachedCriteria.forClass(entityClass);
    }

    protected List<E> findByCriteria(DetachedCriteria detachedCriteria) {
        return findByCriteria(detachedCriteria, -1, -1);
    }

    @SuppressWarnings("unchecked")
    protected List<E> findByCriteria(DetachedCriteria detachedCriteria, int offset, int limit) {
        Criteria executableCriteria = detachedCriteria.getExecutableCriteria(getSession());
        if (offset > 0) {
            executableCriteria.setFirstResult(offset);
        }
        if (limit > 0) {
            executableCriteria.setMaxResults(limit);
        }
        return executableCriteria.list();
    }

    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
