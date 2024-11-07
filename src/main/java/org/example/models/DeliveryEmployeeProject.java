package org.example.models;


public class DeliveryEmployeeProject {
    private int deliveryEmployeeId;
    private int projectId;

    public DeliveryEmployeeProject(final int deliveryEmployeeId, final int projectId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.projectId = projectId;
    }
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(int deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }
}
