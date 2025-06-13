package org.telran.ticketApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telran.ticketApp.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
