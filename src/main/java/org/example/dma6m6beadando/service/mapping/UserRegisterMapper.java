package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.RegisterDto;
import org.example.dma6m6beadando.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConf.class)
public interface UserRegisterMapper extends ToEntity<UserEntity, RegisterDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "projects", ignore = true)
    UserEntity ToEnt(RegisterDto registerDto);
}
