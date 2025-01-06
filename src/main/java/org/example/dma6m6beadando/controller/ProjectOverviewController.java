package org.example.dma6m6beadando.controller;

import org.example.dma6m6beadando.Dto.FinishedObjectId;
import org.example.dma6m6beadando.Dto.UserListDTO;
import org.example.dma6m6beadando.service.AuthService;
import org.example.dma6m6beadando.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/overview",produces = MediaType.APPLICATION_JSON_VALUE)

public class ProjectOverviewController {

    private final AuthService authService;
    private final EntityService entityService;

    public ProjectOverviewController(AuthService authService, EntityService entityService) {
        this.authService = authService;
        this.entityService = entityService;
    }




    @GetMapping(value = "getOverview",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getOverview() {

        var user = authService.getCurrentUser();
        user.setProjects(entityService.getProjectsForUser(user.getId()));

        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "getUsers",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<List<UserListDTO>> getUsers() {
        return ResponseEntity.ok(entityService.GetUserList());
    }

    @PostMapping(value = "getProjParticipants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserListDTO>> getProjParticipants(@RequestBody FinishedObjectId projId){

        return ResponseEntity.ok(entityService.getProjParticipants(projId.getProjId()));
    }


}
