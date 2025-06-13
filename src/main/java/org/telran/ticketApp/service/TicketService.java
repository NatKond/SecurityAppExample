package org.telran.ticketApp.service;

import org.telran.ticketApp.entity.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getAllTickets();

    Ticket getTicketById(int id);

    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    void deleteTicketById(int id);
}
