package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.UserListDTO;
import org.example.dma6m6beadando.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapperConf.class)
public interface UserListDTOMapper extends ToDto<UserEntity, UserListDTO>{




}
