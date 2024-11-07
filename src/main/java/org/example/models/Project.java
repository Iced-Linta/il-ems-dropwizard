package org.example.models;

import java.sql.Date;

public class Project {
    private int projectId;
    private String name;
    private float value;
    private int techLeadId;
    private int clientId;
    private int salesEmployeeId;
    private Date startDate;
    private Date finishDate;

    public Project(final int projectId, final String name, final float value, final int techLeadId,
                   final int clientId, final int salesEmployeeId, final Date startDate,
                   final Date finishDate) {
        this.projectId = projectId;
        this.name = name;
        this.value = value;
        this.techLeadId = techLeadId;
        this.clientId = clientId;
        this.salesEmployeeId = salesEmployeeId;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(final int projectId) {
        this.projectId = projectId;
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

    public void setTechLeadId(final int techleadId) {
        this.techLeadId = techleadId;
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
