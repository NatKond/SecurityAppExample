package org.telran.ticketApp.converter;

public interface Converter<Entity, RequestDto, ResponseDto> {

    Entity convertDtoToEntity(RequestDto requestDto);

    ResponseDto convertEntityToDto(Entity entity);
}
