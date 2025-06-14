package org.telran.ticketApp.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.repository.LocalUserRepository;

import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringBootTest
//@TestPropertySource("classpath:application-test.properties")
//@DirtiesContext
@ActiveProfiles("test")
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class LocalUserIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    //@MockitoBean для теста с мок-объектом вместо localUserRepository
    @Autowired
    LocalUserRepository localUserRepository;

    @Autowired
    ObjectMapper objectMapper;

    static LocalUser localUser1;
    static LocalUser localUser2;
    static LocalUser localUser3;

    @BeforeEach
    void init() {
        localUser1 = new LocalUser(
                1L,
                //null,
                "Hans",
                "Müller",
                "hans.mueller@example.de",
                "password_1",
                "Berliner Str. 55, 10115 Berlin, Germany"
        );

        localUser2 = new LocalUser(
                2L,
                //null,
                "Émilie",
                "Dubois",
                "emilieDubois@example.fr",
                "password_2",
                "12 Rue de Rivoli, 75001 Paris, France"
        );

        localUser3 = new LocalUser(
                3L,
                //null,
                "Luca",
                "Rossi",
                "luca_rossi@example.it",
                "password_3",
                "Via Roma 1, 00100 Roma, Italy"
        );

        //        localUserRepository.saveAll(List.of(localUser1, localUser2, localUser3));
    }

//    @AfterEach
//    void tearDown() {
//        localUserRepository.deleteAll(List.of(localUser1, localUser2, localUser3));
//    }


    @Test
    void findAllIntegrationTest() throws Exception {
        List<LocalUser> localUserListExpected = List.of(localUser1, localUser2, localUser3);

        //when(localUserRepositoryMock.findAll()).thenReturn(localUserListExpected);
        mockMvc.perform(get("/localUser"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$[*].id", hasItems(
                                localUser1.getId().intValue(),
                                localUser2.getId().intValue(),
                                localUser3.getId().intValue())),
                        jsonPath("$[*].name", hasItems(
                                localUser1.getName(),
                                localUser1.getName(),
                                localUser2.getName())),
                        jsonPath("$[*].surname", hasItems(
                                localUser1.getSurname(),
                                localUser1.getSurname(),
                                localUser2.getSurname())),
                        jsonPath("$[*].email", hasItems(
                                localUser1.getEmail(),
                                localUser1.getEmail(),
                                localUser2.getEmail())),
                        jsonPath("$[*].password", hasItems(
                                localUser1.getPassword(),
                                localUser1.getPassword(),
                                localUser2.getPassword())),
                        jsonPath("$[*].postAddress", hasItems(
                                localUser1.getPostAddress(),
                                localUser1.getPostAddress(),
                                localUser2.getPostAddress()
                        )));

        //verify(localUserRepositoryMock).findAll();
    }

    @Test
    void findByIdIntegrationTest() throws Exception {
        LocalUser localUserExpected = localUser1;
        mockMvc.perform(get("/localUser/" + localUserExpected.getId()))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(localUserExpected))
                );
    }

    @Test
    void createIntegrationTest() throws Exception {

        LocalUser localUserExpected = new LocalUser(
                null,
                "Sofia",
                "Nowak",
                "sofia.nowak@example.pl",
                "password_4",
                "ul. Marszałkowska 10, 00-590 Warszawa, Poland"
        );

        mockMvc.perform(post("/localUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localUserExpected)))
                .andDo(print())
                .andExpectAll(
                        status().isAccepted(),
                        jsonPath("$.id").exists(),
                        jsonPath("$.name").value(localUserExpected.getName()),
                        jsonPath("$.surname").value(localUserExpected.getSurname())
                );
    }

    @Test
    void updateIntegrationTest() throws Exception {

        LocalUser localUserExpected = new LocalUser(
                null,
                "Carlos",
                "García",
                "carlos.garcia@example.es",
                "password_6",
                "Calle de Alcalá 45, 28014 Madrid, Spain"
        );

        mockMvc.perform(put("/localUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localUserExpected)))
                .andDo(print())
                .andExpectAll(
                        status().isAccepted(),
                        jsonPath("$.id").exists(),
                        jsonPath("$.name").value(localUserExpected.getName()),
                        jsonPath("$.surname").value(localUserExpected.getSurname()),
                        jsonPath("$.email").value(localUserExpected.getEmail()),
                        jsonPath("$.password").value(localUserExpected.getPassword()),
                        jsonPath("$.postAddress").value(localUserExpected.getPostAddress())
                );

        localUserExpected.setEmail("carlos.garcia25@example.es");

        mockMvc.perform(put("/localUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localUserExpected)))
                .andDo(print())
                .andExpectAll(
                        status().isAccepted(),
                        jsonPath("$.id").exists(),
                        jsonPath("$.name").value(localUserExpected.getName()),
                        jsonPath("$.surname").value(localUserExpected.getSurname()),
                        jsonPath("$.email").value(localUserExpected.getEmail()),
                        jsonPath("$.password").value(localUserExpected.getPassword()),
                        jsonPath("$.postAddress").value(localUserExpected.getPostAddress())
                );
    }
}
