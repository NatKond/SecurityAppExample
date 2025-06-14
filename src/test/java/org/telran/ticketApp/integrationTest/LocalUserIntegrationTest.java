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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.repository.LocalUserRepository;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
//@DirtiesContext
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

    //    @BeforeAll
//    static void setUp() {
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
    }
//    @BeforeEach
//    void init() {
//        localUserRepository.saveAll(List.of(localUser1, localUser2, localUser3));
//    }
//
//    @AfterEach
//    void tearDown() {
//        localUserRepository.deleteAll(List.of(localUser1, localUser2, localUser3));
//    }


    @Test
    void findAll() throws Exception {
        List<LocalUser> localUserListExpected = List.of(localUser1, localUser2, localUser3);

        //when(localUserRepositoryMock.findAll()).thenReturn(localUserListExpected);
        mockMvc.perform(get("/localUser"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(localUserListExpected))
                );

        //verify(localUserRepositoryMock).findAll();
    }

    @Test
    void findById() throws Exception {
        LocalUser localUserExpected = localUser1;
        mockMvc.perform(get("/localUser/" + localUserExpected.getId()))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(localUserExpected))
                );

    }

    @Test
    void update() throws Exception {

        LocalUser localUserExpected = localUser1;
        localUser1.setEmail("hans.mueller25@example.de");
        localUser1.setPassword("password_1_new");

        mockMvc.perform(post("/localUser")
                        .content(objectMapper.writeValueAsString(localUserExpected)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(localUserExpected))
                );
    }
}
