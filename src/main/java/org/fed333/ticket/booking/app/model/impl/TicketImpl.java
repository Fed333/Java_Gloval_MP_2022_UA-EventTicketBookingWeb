package org.fed333.ticket.booking.app.model.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fed333.ticket.booking.app.model.Ticket;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketImpl implements Ticket {

    private Long id;

    private Long eventId;

    private Long userId;

    private Category category;

    private int place;

    private boolean cancelled;

}
