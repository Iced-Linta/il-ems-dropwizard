package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class ProjectRequest {
    private String name;
    private float value;
    private int techleadId;
    private int clientId;
    private int salesEmployeeId;
    private Date startDate;
    private Date finishDate;
    private float commissionRate;
    @JsonCreator
    public ProjectRequest(
            @JsonProperty("projectId") final int projectId,
            @JsonProperty("name") final String name,
            @JsonProperty("value") final float value,
            @JsonProperty("techLeadId") final int techLeadId,
            @JsonProperty("clientId") final int clientId,
            @JsonProperty("salesEmployeeId") final int salesEmployeeId,
            @JsonProperty("startDate") final Date startDate,
            @JsonProperty("finishDate") final Date finishDate,
            @JsonProperty("commissionRate") final float commissionRate)
    {
        this.name = name;
        this.value = value;
        this.techleadId = techLeadId;
        this.clientId = clientId;
        this.salesEmployeeId = salesEmployeeId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.commissionRate = commissionRate;
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
        return techleadId;
    }

    public void setTechLeadId(final int techLeadId) {
        this.techleadId = techleadId;
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

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(final float commissionRate) {
        this.commissionRate = commissionRate;
    }
}

