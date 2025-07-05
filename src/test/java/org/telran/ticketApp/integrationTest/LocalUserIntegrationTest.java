package org.telran.ticketApp.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.enums.Role;

import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;


@FieldDefaults(level = AccessLevel.PRIVATE)
@SpringBootTest
//@ActiveProfiles("test")
//@TestPropertySource("/application-test.properties")
//@Sql("/data.sql")
//@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //перезапускает контекст (в т.ч. базу данных) после каждого теста
@Transactional // Spring откатывает транзакцию после каждого теста
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class LocalUserIntegrationTest {
    @Autowired
    MockMvc mockMvc;

//    @MockitoBean // тесты с мок-объектом вместо localUserRepository
//    @Autowired
//    LocalUserRepository localUserRepository;

    @Autowired
    ObjectMapper objectMapper;

    LocalUser localUser1;
    LocalUser localUser2;
    LocalUser localUser3;
    LocalUser localUser4;

    @BeforeEach
    void init() {
        localUser1 = new LocalUser(
                1L,
                //null,
                "Hans",
                "Müller",
                "hans.mueller@example.de",
                "password_1",
                "Berliner Str. 55, 10115 Berlin, Germany",
                Role.ROLE_USER,
                new HashSet<>()
        );
        localUser2 = new LocalUser(
                2L,
                //null,
                "Émilie",
                "Dubois",
                "emilieDubois@example.fr",
                "password_2",
                "12 Rue de Rivoli, 75001 Paris, France",
                Role.ROLE_USER,
                new HashSet<>()
        );
        localUser3 = new LocalUser(
                3L,
                //null,
                "Luca",
                "Rossi",
                "luca_rossi@example.it",
                "password_3",
                "Via Roma 1, 00100 Roma, Italy",
                Role.ROLE_USER,
                new HashSet<>()
        );

        localUser4 = new LocalUser(
                4L,
                //null,
                "Carlos",
                "García",
                "carlos.garcia@example.es",
                "password_4",
                "Calle de Alcalá 45, 28014 Madrid, Spain",
                Role.ROLE_USER,
                new HashSet<>()
        );
//        localUser1 = localUserRepository.save(localUser1);
//        localUser2 = localUserRepository.save(localUser2);
//        localUser3 = localUserRepository.save(localUser3);
//        localUser4 = localUserRepository.save(localUser4);
    }

//    @AfterEach
//    void tearDown() {
//        localUserRepository.deleteAll();
//    }

    @Test
    void findAllIntegrationTest() throws Exception {
        List<LocalUser> localUserListExpected = List.of(localUser1, localUser2, localUser3, localUser4);

        //when(localUserRepositoryMock.findAll()).thenReturn(localUserListExpected);
        mockMvc.perform(get("/users")
                .with(httpBasic("alex", "12345")))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        //content().json(objectMapper.writeValueAsString(localUserListExpected)));
                        jsonPath("$[*].name", hasItems(
                                localUser1.getName(),
                                localUser2.getName(),
                                localUser3.getName())));

        //verify(localUserRepositoryMock).findAll();
    }

    @Test
    void findByIdIntegrationTest() throws Exception {
        LocalUser localUserExpected = localUser1;
        mockMvc.perform(get("/users/"+ localUserExpected.getId())
                .with(httpBasic("alex", "12345")))
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
                "ul. Marszałkowska 10, 00-590 Warszawa, Poland",
                Role.ROLE_USER,
                new HashSet<>()
        );

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localUserExpected))
                        .with(httpBasic("alex", "12345")))
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

        LocalUser localUserExpected = localUser1;

        localUserExpected.setEmail("hans.mueller25@example.de");
        localUserExpected.setPassword("password_1_new");

        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localUserExpected))
                        .with(httpBasic("alex", "12345")))
                .andDo(print())
                .andExpectAll(
                        status().isAccepted(),
                        jsonPath("$.id").exists(),
                        jsonPath("$.name").value(localUserExpected.getName()),
                        jsonPath("$.surname").value(localUserExpected.getSurname()),
                        jsonPath("$.email").value(localUserExpected.getEmail()),
                        jsonPath("$.password").value(localUserExpected.getPassword())
                );
    }

    @Test
    void deleteIntegrationTest() throws Exception {
        mockMvc.perform(delete("/users/" + localUser1.getId())
                        .with(httpBasic("alex", "12345")))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/users/" + localUser1.getId())
                        .with(httpBasic("alex", "12345")))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
