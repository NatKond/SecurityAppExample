package org.telran.ticketApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telran.ticketApp.converter.Converter;
import org.telran.ticketApp.dto.CreateUserRequestDto;
import org.telran.ticketApp.dto.UserResponseDto;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.exception.LocalUserNotFoundException;
import org.telran.ticketApp.service.LocalUserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class LocalUserController implements LocalUserControllerInt {

    private final LocalUserService localUserService;

    private final Converter<LocalUser, CreateUserRequestDto, UserResponseDto> converter;

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
    public ResponseEntity<UserResponseDto> createLocalUser(@RequestBody CreateUserRequestDto createUserRequestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        converter.convertEntityToDto(
                                localUserService.save(
                                        converter.convertDtoToEntity(createUserRequestDto))));
    }

    @PutMapping
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
