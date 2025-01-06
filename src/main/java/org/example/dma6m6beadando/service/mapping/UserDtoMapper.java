package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.UserDTO;
import org.example.dma6m6beadando.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapperConf.class,uses = {RoleMapper.class,ProjectMapper.class})
public interface UserDtoMapper extends ToDto<UserEntity, UserDTO>{


    UserDTO toDto(UserEntity entity);

    @Mapping(target = "roles",source = "roles")
    @Mapping(target = "projects",ignore = true)
    UserDTO mapToUserDto(UserEntity entity);


}
