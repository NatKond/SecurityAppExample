package org.telran.ticketApp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.telran.ticketApp.dto.CreateUserRequestDto;
import org.telran.ticketApp.dto.UserResponseDto;
import org.telran.ticketApp.entity.LocalUser;
import org.telran.ticketApp.enums.Role;

@Component
public class UserConverter implements Converter<LocalUser, CreateUserRequestDto, UserResponseDto> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LocalUser convertDtoToEntity(CreateUserRequestDto createUserRequestDto) {
        return LocalUser.builder()
                .name(createUserRequestDto.name())
                .surname(createUserRequestDto.surname())
                .email(createUserRequestDto.email())
                .password(passwordEncoder.encode(createUserRequestDto.password()))
                .postAddress(createUserRequestDto.postAddress())
                .tickets(createUserRequestDto.tickets())
                .role(Role.ROLE_USER)
                .build();
    }

    @Override
    public UserResponseDto convertEntityToDto(LocalUser localUser) {
        return new UserResponseDto(
                localUser.getId(),
                localUser.getName(),
                localUser.getSurname());
    }
}
