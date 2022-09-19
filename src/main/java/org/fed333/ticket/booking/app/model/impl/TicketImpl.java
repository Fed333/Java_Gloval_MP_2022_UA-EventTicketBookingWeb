package org.fed333.ticket.booking.app.model.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;
import org.fed333.ticket.booking.app.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class TicketImpl implements Ticket {

    @Id
    private Long id;

    private Long eventId;

    @OneToOne(targetEntity = EventImpl.class)
    private Event event;

    private Long userId;

    @OneToOne(targetEntity = UserImpl.class)
    private User user;

    private Category category;

    private int place;

    private boolean cancelled;

}
