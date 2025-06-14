package org.telran.ticketApp.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.exception.LocalUserNotFoundException;
import org.telran.ticketApp.repository.LocalUserRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class LocalUserServiceImpl implements LocalUserService {

    private final LocalUserRepository localUserRepository;

    @Override
    public List<LocalUser> findAll() {
        return localUserRepository.findAll();
    }

    @Override
    public LocalUser findById(Long id) {
        return localUserRepository.findById(id).orElseThrow(() -> new LocalUserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public LocalUser save(LocalUser localUser) {
        return localUserRepository.save(localUser);
    }

    @Override
    public LocalUser update(LocalUser localUser) {
        return localUserRepository.save(localUser);
    }

    @Override
    public void deleteById(Long id) {
        localUserRepository.deleteById(id);
    }
}
