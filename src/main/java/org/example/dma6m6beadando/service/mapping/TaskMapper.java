package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.TaskDTO;
import org.example.dma6m6beadando.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapperConf.class)
public interface TaskMapper extends ToDto<Task, TaskDTO>{

    @Mapping(target = "sprintId", source = "sprint.id")
    @Mapping(target = "status",source = "status")
    TaskDTO toDto(Task task);

    List<TaskDTO> toDto(List<Task> tasks);

}
