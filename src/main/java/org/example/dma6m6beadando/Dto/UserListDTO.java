package org.example.dma6m6beadando.Dto;

import java.util.UUID;

public class UserListDTO {

    private UUID id;

    private String username;

    public UserListDTO(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

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
}
