package org.telran.ticketApp.service;

import org.telran.ticketApp.entity.LocalUser;

import java.util.List;

public interface LocalUserService {

    List<LocalUser> findAll();

    LocalUser findById(Long id);

    LocalUser save(LocalUser localUser);

    LocalUser update(LocalUser localUser);

    void deleteById(Long id);
}
