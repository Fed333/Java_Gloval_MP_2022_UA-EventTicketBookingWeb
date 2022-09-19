package org.fed333.ticket.booking.app.repository.impl.old;

import lombok.Getter;
import lombok.Setter;
import org.fed333.ticket.booking.app.model.User;
import org.fed333.ticket.booking.app.repository.UserRepository;
import org.fed333.ticket.booking.app.repository.impl.old.component.LongIdGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl extends AbstractCrudDao<User, Long> implements UserRepository {

    @Getter @Setter
    private LongIdGenerator idGenerator;

    @Override
    protected Long nextId() {
        return idGenerator.generateNextId();
    }

    @Override
    public List<User> getAllByName(String name) {
        return getAll().stream().filter(u->u.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<User> getAllByEmail(String email) {
        return getAll().stream().filter(u->u.getEmail().equals(email)).collect(Collectors.toList());
    }
}
