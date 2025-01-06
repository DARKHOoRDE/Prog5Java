package org.example.dma6m6beadando.Dto;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Date;
import java.util.UUID;

public class NewSprintDTO {

    private String name;
    private String description;
    private UUID projId;
    private Date endDate;
    private Date startDate;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getProjId() {
        return projId;
    }

    public void setProjId(UUID projId) {
        this.projId = projId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
