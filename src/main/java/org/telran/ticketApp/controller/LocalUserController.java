package org.telran.ticketApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.exception.LocalUserNotFoundException;
import org.telran.ticketApp.service.LocalUserService;

import java.util.List;

@RestController
@RequestMapping(value = "/localUser")
@RequiredArgsConstructor
public class LocalUserController {

    private final LocalUserService localUserService;

    @GetMapping
    public Iterable<LocalUser> getAllLocalUsers() {
        List<LocalUser> localUserList = localUserService.findAll();
//        System.out.println(localUserList);
        return localUserList;
    }

    @GetMapping("/{id}")
    public LocalUser getLocalUser(@PathVariable Long id) {
        return localUserService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<LocalUser> createLocalUser(@RequestBody LocalUser localUser) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(localUserService.save(localUser));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<LocalUser> updateLocalUser(@RequestBody LocalUser localUser) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(localUserService.update(localUser));
    }

    @DeleteMapping("/{id}")
    public void deleteLocalUser(@PathVariable Long id) {
        localUserService.deleteById(id);
    }

    @ExceptionHandler(LocalUserNotFoundException.class)
    public ResponseEntity<String> handlerLocalUserNotFoundException(LocalUserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product controller: " + exception.getMessage());
    }
}
