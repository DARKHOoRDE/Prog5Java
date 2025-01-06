package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.TaskDTO;
import org.example.dma6m6beadando.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConf.class)
public interface TaskMapperToEnt extends ToEntity<Task, TaskDTO>{

    @Mapping(target = "sprint", ignore = true)
    Task ToEnt(TaskDTO dto);

}
