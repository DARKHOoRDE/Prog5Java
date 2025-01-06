package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.SprintDTO;
import org.example.dma6m6beadando.entity.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapperConf.class,uses = {TaskMapper.class})
public interface SprintMapper extends ToDto<Sprint, SprintDTO>{

    @Mapping(target = "projectId", source = "project.id")
    @Mapping(target = "tasks", source = "tasks")
    SprintDTO toDto(Sprint sprint);

    List<SprintDTO> toDto(List<Sprint> sprints);

}
