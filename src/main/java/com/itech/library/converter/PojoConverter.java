package com.itech.library.converter;

public interface PojoConverter<Pojo, Entity> {

    Pojo entityToPojo(Entity entity);

    Entity pojoToEntity(Pojo pojo);
}
