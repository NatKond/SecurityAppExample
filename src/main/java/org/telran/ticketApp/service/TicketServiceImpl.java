package org.telran.ticketApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telran.ticketApp.entity.Ticket;
import org.telran.ticketApp.exception.TicketNotFindException;
import org.telran.ticketApp.repository.TicketRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFindException("Ticket with id " + id + " not found"));
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicketById(int id) {
        ticketRepository.deleteById(id);
    }
}
