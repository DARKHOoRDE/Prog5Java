package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.RoleDTO;
import org.example.dma6m6beadando.entity.Roles;
import org.mapstruct.Mapper;

@Mapper(config = MapperConf.class)
public interface RoleMapper extends ToDto<Roles, RoleDTO>{
}
