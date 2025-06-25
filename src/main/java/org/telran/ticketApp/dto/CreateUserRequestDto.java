package org.telran.ticketApp.dto;

import org.telran.ticketApp.entity.Ticket;

import java.util.Set;

public record CreateUserRequestDto (
        String name,
        String surname,
        String email,
        String password,
        String postAddress,
        Set<Ticket> tickets
) {}
