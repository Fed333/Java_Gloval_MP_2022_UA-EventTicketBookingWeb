package org.fed333.ticket.booking.app.model.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fed333.ticket.booking.app.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity User implementation.
 * @author Roman_Kovalchuk
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserImpl implements User {

    @Id
    private Long id;

    private String name;

    private String email;

}
