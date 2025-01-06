package org.example.dma6m6beadando.controller;

import org.example.dma6m6beadando.Dto.*;
import org.example.dma6m6beadando.service.EntityService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/entity",produces = MediaType.APPLICATION_JSON_VALUE)
public class EntityController {

    private final EntityService entityService;

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }


    @PostMapping("finishProject")
    public void finishProject(@RequestBody FinishedObjectId finishedObjectId) {
        this.entityService.FinishProject(finishedObjectId.getProjId());
    }

    @PostMapping("createProj")
    public void createProject(@RequestBody NewProjDTO projDTO) {
        this.entityService.CreateProj(projDTO);
    }

    @PostMapping("createNewSprint")
    public ResponseEntity<?> createNewSprint(@RequestBody NewSprintDTO sprintDTO) {
        return ResponseEntity.ok( entityService.createNewSprint(sprintDTO));
    }

    @PostMapping("createNewTask")
    public ResponseEntity<TaskDTO> createNewTask(@RequestBody NewTaskDTO taskDTO) {
        var task = entityService.createTask(taskDTO);
        return ResponseEntity.ok(task);
    }

    @PostMapping("assignToUser")
    public ResponseEntity<TaskDTO> assignToUser(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok( entityService.assignToUser(taskDTO));
    }

    @PutMapping("moveTask")
    public ResponseEntity<TaskDTO> moveTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok( entityService.moveTask(taskDTO));
    }

    @PutMapping("unassignTask")
    public ResponseEntity<TaskDTO> unassignTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok( entityService.unassignTask(taskDTO));
    }

    @DeleteMapping("deleteTask/{taskId}/{sprintId}")
    public void deleteTask(@PathVariable UUID taskId, @PathVariable UUID sprintId) {
        this.entityService.deleteTask(taskId, sprintId);
    }

}
