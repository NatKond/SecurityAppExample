package org.telran.ticketApp.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.telran.ticketApp.dto.CreateUserRequestDto;
import org.telran.ticketApp.dto.UserResponseDto;
import org.telran.ticketApp.entity.LocalUser;

public interface LocalUserControllerInt {

    Iterable<LocalUser> getAllLocalUsers();

    LocalUser getLocalUser(Long id);

    ResponseEntity<UserResponseDto> createLocalUser(
            @RequestBody(
                    description = "New user example",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples =
                            {
                            @ExampleObject(
                                    name = "Hans",
                                    summary = "User example 1",
                                    value = "{" +
                                            "\"name\": \"Hans\", " +
                                            "\"surname\": \"Müller\", " +
                                            "\"email\": \"hm@e.de\"," +
                                            "\"password\": \"12345\"," +
                                            "\"postAddress\": \"Berliner Str. 55, 10115 Berlin, Germany\"" +
                                            "}"
                            ),
                            @ExampleObject(
                                    name = "Émilie",
                                    summary = "User example 2",
                                    value = "{" +
                                            "\"name\": \"Émilie\", " +
                                            "\"surname\": \"Dubois\", " +
                                            "\"email\": \"emi@e.fr\"," +
                                            "\"password\": \"12345\"," +
                                            "\"postAddress\": \"12 Rue de Rivoli, 75001 Paris, France\"" +
                                            "}"
                            )}
                    )
            )
            CreateUserRequestDto createUserRequestDto);

    ResponseEntity<LocalUser> updateLocalUser(LocalUser localUser);

    void deleteLocalUser(Long id);
}
