package org.telran.ticketApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.exception.LocalUserNotFoundException;

import java.util.Collections;

@Service
public class LocalUserDetailsService implements UserDetailsService {

    @Autowired
    private LocalUserService localUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LocalUser localUser = localUserService.findByEmail(email)
                .orElseThrow(() -> new LocalUserNotFoundException("User with email " + email + " not found."));

        return new User(email, localUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(localUser.getRole().name())));
    }
}
