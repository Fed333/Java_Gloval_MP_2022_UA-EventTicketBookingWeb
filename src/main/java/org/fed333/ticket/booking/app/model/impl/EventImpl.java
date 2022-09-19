package org.fed333.ticket.booking.app.model.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fed333.ticket.booking.app.model.Event;
import org.fed333.ticket.booking.app.model.Ticket;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class EventImpl implements Event {

    @Id
    private Long id;

    private String title;

    private Date date;

    @OneToMany(targetEntity = TicketImpl.class, mappedBy = "event", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

}
