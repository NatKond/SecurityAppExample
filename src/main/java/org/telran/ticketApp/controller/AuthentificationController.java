package org.telran.ticketApp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telran.ticketApp.security.model.LoginRequest;
import org.telran.ticketApp.security.model.LoginResponse;

@RestController
@RequestMapping("login")
public class AuthentificationController {

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return null;
    }
}
