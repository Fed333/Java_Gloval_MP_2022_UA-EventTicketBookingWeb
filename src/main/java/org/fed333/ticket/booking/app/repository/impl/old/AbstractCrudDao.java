package org.fed333.ticket.booking.app.repository.impl.old;

import org.fed333.ticket.booking.app.model.Identifiable;
import org.fed333.ticket.booking.app.repository.CrudRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static java.util.Objects.nonNull;

/**
 * Common abstract Dao class for repository implementations.
 * @author Roman_Kovalchuk
 * */
public abstract class AbstractCrudDao<E extends Identifiable<ID>, ID> implements CrudRepository<E, ID> {

    private final Map<ID, E> data = new HashMap<>();

    /**
     * Generates next id.
     * */
    protected abstract ID nextId();

    public E save(E e){
        if (Objects.isNull(e.getId())){
            e.setId(nextId());
        }
        data.put(e.getId(), e);
        return e;
    }

    @Override
    public boolean existsById(ID id) {
        return nonNull(getById(id));
    }

    @Override
    public E getById(ID id) {
        return data.get(id);
    }

    @Override
    public List<E> getAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public E remove(E e) {
        return data.remove(e.getId());
    }

    @Override
    public E remove(ID id) {
        return data.remove(id);
    }

    protected Map<ID, E> getMap(){
        return data;
    }

}
