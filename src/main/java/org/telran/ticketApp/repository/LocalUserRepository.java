package org.telran.ticketApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telran.ticketApp.entity.LocalUser;

import java.util.Optional;

public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {

    Optional<LocalUser> findByEmail(String email);
}
