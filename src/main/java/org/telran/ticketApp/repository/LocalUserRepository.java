package org.telran.ticketApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telran.ticketApp.entity.LocalUser;

public interface LocalUserRepository extends JpaRepository<LocalUser, Long> {
}
