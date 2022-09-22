package org.fed333.ticket.booking.app.model;

import lombok.*;

import javax.persistence.*;

/**
 * Entity User implementation.
 * @author Roman_Kovalchuk
 * */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

}
