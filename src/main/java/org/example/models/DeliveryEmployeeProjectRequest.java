package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryEmployeeProjectRequest {

    private int deliveryEmployeeId;
    private int projectId;

    @JsonCreator
    public DeliveryEmployeeProjectRequest(
            @JsonProperty("deliveryEmployeeId") final int deliveryEmployeeId,
            @JsonProperty("projectId") final int projectId
    ) {
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.projectId = projectId;
    }
    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(int deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
