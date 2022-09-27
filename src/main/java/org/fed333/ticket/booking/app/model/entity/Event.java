package org.fed333.ticket.booking.app.model.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


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
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Event implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private Date date;

    @OneToMany(targetEntity = Ticket.class, mappedBy = "event", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @Column(name = "ticket_price")
    private double ticketPrice;

}
