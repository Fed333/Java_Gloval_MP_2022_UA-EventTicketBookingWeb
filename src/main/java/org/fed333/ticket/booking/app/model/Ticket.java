package org.fed333.ticket.booking.app.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    @OneToOne(targetEntity = Event.class)
    private Event event;

    private Long userId;

    @OneToOne(targetEntity = User.class)
    private User user;

    private Category category;

    private int place;

    private boolean cancelled;

    public enum Category {STANDARD, PREMIUM, BAR}

}
