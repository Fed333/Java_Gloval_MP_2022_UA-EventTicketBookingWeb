package org.fed333.ticket.booking.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date date;

    @OneToMany(targetEntity = Ticket.class, mappedBy = "event", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

}
