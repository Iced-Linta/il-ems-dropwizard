package org.example.mappers;

import org.example.models.DeliveryEmployee;
import org.example.responses.DeliveryEmployeeResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryEmployeeMapper {
    public static DeliveryEmployeeResponse
    mapDeliveryEmployeeToDeliveryEmployeeResponse(
            final DeliveryEmployee deliveryEmployee) {
        return new DeliveryEmployeeResponse(
                deliveryEmployee.getDeliveryEmployeeId(),
                deliveryEmployee.getName(),
                deliveryEmployee.getSalary(),
                deliveryEmployee.getBankAccountNumber(),
                deliveryEmployee.getNationalInsuranceNumber()
        );
    }

    public static List<DeliveryEmployeeResponse>
    mapDeliveryEmployeeListToDeliveryEmployeeResponseList(
            final List<DeliveryEmployee> deliveryEmployees) {
        return deliveryEmployees.stream()
                .map(DeliveryEmployeeMapper::
                        mapDeliveryEmployeeToDeliveryEmployeeResponse)
                .collect(Collectors.toList());
    }
}
