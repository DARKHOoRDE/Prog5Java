package org.example.dma6m6beadando.Dto;

import java.util.List;
import java.util.UUID;

public class NewProjDTO {
    private String name;
    private List<UUID> userIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<UUID> userIds) {
        this.userIds = userIds;
    }
}
