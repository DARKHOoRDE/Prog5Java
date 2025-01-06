package org.example.dma6m6beadando.service.mapping;

import org.example.dma6m6beadando.Dto.ProjectDTO;
import org.example.dma6m6beadando.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(config = MapperConf.class, uses = {SprintMapper.class,TaskMapper.class})
public interface ProjectMapper extends ToDto<Project, ProjectDTO> {

    @Mapping(target = "sprints", source = "sprints")
    ProjectDTO toDto(Project project);


}
