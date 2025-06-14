package org.telran.ticketApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.repository.LocalUserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
class LocalUserServiceImplTest {

    @Mock
    LocalUserRepository localUserRepositoryMock;

    @InjectMocks
    LocalUserServiceImpl localUserServiceImpl;

    static LocalUser localUser1;
    static LocalUser localUser2;
    static LocalUser localUser3;

    @BeforeEach
    void init() {
        localUser1 = new LocalUser(
                1L,
                "Hans",
                "Müller",
                "hans.mueller@example.de",
                "EQKNTMpdmzihisss4gp8",
                "Berliner Str. 55, 10115 Berlin, Germany"
        );

        localUser2 = new LocalUser(
                2L,
                "Émilie",
                "Dubois",
                "emilieDubois@example.fr",
                "t4HbG8u6X9oWHPed348q",
                "12 Rue de Rivoli, 75001 Paris, France"
        );

        localUser3 = new LocalUser(
                3L,
                "Luca",
                "Rossi",
                "luca_rossi@example.it",
                "dgtwz7xj644og7oemdc4",
                "Via Roma 1, 00100 Roma, Italy"
        );
    }

    @Test
    void findAll() throws Exception {
        List<LocalUser> localUserListExpected = List.of(localUser1, localUser2, localUser3);

        when(localUserRepositoryMock.findAll()).thenReturn(localUserListExpected);

        List<LocalUser> localUserListActual = localUserServiceImpl.findAll();

        assertNotNull(localUserListActual);
        assertEquals(3, localUserListActual.size());
        assertEquals(localUserListExpected.getFirst(), localUserListActual.getFirst());
        assertEquals(localUserListExpected.get(1), localUserListActual.get(1));
        assertEquals(localUserListExpected.getLast(), localUserListActual.getLast());
        assertEquals(localUserListExpected, localUserListActual);

        verify(localUserRepositoryMock).findAll();
    }

    @Test
    void findById() {
        LocalUser localUserExpected = localUser1;

        when(localUserRepositoryMock.findById(1L)).thenReturn(Optional.of(localUser1));

        LocalUser localUserActual = localUserServiceImpl.findById(1L);

        assertNotNull(localUserActual);
        assertEquals(localUserExpected, localUserActual);
        verify(localUserRepositoryMock).findById(1L);
    }

    @Test
    void save() {
        LocalUser localUserExpected = localUser1;

        when(localUserRepositoryMock.save(localUserExpected)).thenReturn(localUserExpected);

        LocalUser localUserActual = localUserServiceImpl.save(localUserExpected);

        assertNotNull(localUserActual);
        assertEquals(localUserExpected, localUserActual);
        verify(localUserRepositoryMock).save(localUserExpected);
    }

    @Test
    void update() {
        LocalUser localUserExpected = localUser1;
        localUserExpected.setEmail("hans.mueller25@example.de");
        localUserExpected.setPassword("password_1_new");

        when(localUserRepositoryMock.save(localUserExpected)).thenReturn(localUserExpected);

        LocalUser localUserActual = localUserServiceImpl.update(localUserExpected);

        assertNotNull(localUserActual);
        assertEquals(localUserExpected, localUserActual);
    }

    @Test
    void deleteById() {
        localUserServiceImpl.deleteById(1L);

        verify(localUserRepositoryMock).deleteById(1L);
    }
}