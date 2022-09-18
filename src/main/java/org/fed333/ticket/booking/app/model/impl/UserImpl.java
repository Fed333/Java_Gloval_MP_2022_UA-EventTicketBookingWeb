package org.fed333.ticket.booking.app.model.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fed333.ticket.booking.app.model.User;

/**
 * Entity User implementation.
 * @author Roman_Kovalchuk
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImpl implements User {

    private Long id;

    private String name;

    private String email;

}
