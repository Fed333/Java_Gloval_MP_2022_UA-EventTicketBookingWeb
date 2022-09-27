package org.fed333.ticket.booking.app.repository;

import org.fed333.ticket.booking.app.model.entity.User;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long>{

    List<User> getAllByName(String name);

    List<User> getAllByName(String name, int offset, int size);

    List<User> getAllByEmail(String email);

}
