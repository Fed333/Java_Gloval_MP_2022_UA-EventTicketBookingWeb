package org.fed333.ticket.booking.app.repository;

import org.fed333.ticket.booking.app.model.Identifiable;

import java.util.List;

public interface CrudRepository<E extends Identifiable<ID>, ID> {

    boolean existsById(ID id);

    E getById(ID id);

    List<E> getAll();

    E save(E e);

    E remove(E e);

    E remove(ID id);
}
