package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.NewSprintDTO;
import org.example.dma6m6beadando.entity.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConf.class)
public interface NewSprintMapper extends ToEntity<Sprint, NewSprintDTO> {

    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    Sprint ToEnt(NewSprintDTO dto);

}
