package org.example.dma6m6beadando.Dto;

import org.example.dma6m6beadando.entity.Project;
import org.example.dma6m6beadando.entity.Roles;
import org.example.dma6m6beadando.entity.Task;

import java.util.List;
import java.util.UUID;

public class UserDTO {

    private UUID id;

    private String username;

    private List<RoleDTO> roles;

    private List<ProjectDTO> projects;




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}
