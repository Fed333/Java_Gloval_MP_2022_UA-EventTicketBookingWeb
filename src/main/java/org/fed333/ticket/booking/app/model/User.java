package org.fed333.ticket.booking.app.model;

/**
 * Interface which represent User entity.
 * @author Roman_Kovalchuk
 */
public interface User extends Identifiable<Long> {
    /**
     * User Id. UNIQUE.
     * @return User Id.
     */
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    /**
     * User email. UNIQUE.
     * @return User email.
     */
    String getEmail();

    void setEmail(String email);
}
