package com.itech.library.converter;

public interface DtoConverter<Dto, Entity> {

    Dto entityToDto(Entity entity);

    Entity dtoToEntity(Dto dto);
}
