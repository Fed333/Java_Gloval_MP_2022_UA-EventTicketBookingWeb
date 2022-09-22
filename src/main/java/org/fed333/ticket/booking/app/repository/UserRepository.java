package org.fed333.ticket.booking.app.repository;

import org.fed333.ticket.booking.app.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long>{

    List<User> getAllByName(String name);

    List<User> getAllByEmail(String email);

}
