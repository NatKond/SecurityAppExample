package org.telran.ticketApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.telran.ticketApp.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    //find - select, by - where
    Ticket findByTitle(String title);

    List<Ticket> findAllByTitle(String title);

    @Query (nativeQuery = true, value = "SELECT t.* FROM tickets AS t") //LEFT JOIN local_users as u ON u.id = t.id")
    List<Ticket> findAllWithAllUsers();
}
