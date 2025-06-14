package org.telran.ticketApp.controller;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.service.LocalUserService;

@RestController
@RequestMapping(value = "/localUser")
@AllArgsConstructor
public class LocalUserController {

    private final LocalUserService localUserService;

    @GetMapping
    public ResponseEntity<Iterable<LocalUser>> getAllLocalUsers() {
        return ResponseEntity.ok().body(localUserService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalUser> getLocalUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(localUserService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LocalUser> createLocalUser(@RequestBody LocalUser localUser) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(localUserService.save(localUser));
    }

    @PutMapping
    public ResponseEntity<LocalUser> updateLocalUser(@RequestBody LocalUser localUser) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(localUserService.update(localUser));
    }

    @DeleteMapping("/{id}")
    public void deleteLocalUser(@PathVariable Long id) {
        localUserService.deleteById(id);
    }
}
