package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ProjectRequest {
    private String name;
    private float value;
    private int techLeadId;
    private int clientId;
    private int salesEmployeeId;
    private Date startDate;
    private Date finishDate;
    @JsonCreator
    public ProjectRequest(
            final @JsonProperty("name") String name,
            final @JsonProperty("value") float value,
            final @JsonProperty("techLeadId") int techLeadId,
            final @JsonProperty("clientId") int clientId,
            final @JsonProperty("salesEmployeeId") int salesEmployeeId,
            final @JsonProperty("startDate") Date startDate,
            final @JsonProperty("finishDate") Date finishDate)
    {
        this.name = name;
        this.value = value;
        this.techLeadId = techLeadId;
        this.clientId = clientId;
        this.salesEmployeeId = salesEmployeeId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(final float value) {
        this.value = value;
    }

    public int getTechLeadId() {
        return techLeadId;
    }

    public void setTechLeadId(final int techLeadId) {
        this.techLeadId = techLeadId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(final int clientId) {
        this.clientId = clientId;
    }

    public int getSalesEmployeeId() {
        return salesEmployeeId;
    }

    public void setSalesEmployeeId(final int salesEmployeeId) {
        this.salesEmployeeId = salesEmployeeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(final Date finishDate) {
        this.finishDate = finishDate;
    }
}

