package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.NewTaskDTO;
import org.example.dma6m6beadando.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConf.class)
public interface NewTaskMapper extends ToEntity<Task, NewTaskDTO>{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "finishedOn", ignore = true)
    @Mapping(target = "sprint", ignore = true)
    Task ToEnt(NewTaskDTO dto);

}
