package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.DeliveryEmployeeRequest;

public class DeliveryEmployeeValidator {
    public void validateDeliveryEmployee(
            DeliveryEmployeeRequest deliveryEmployeeRequest) throws
            InvalidException {

        if (deliveryEmployeeRequest.getName().length() > 50) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "Name greater than 50 characters.");
        }

        if (deliveryEmployeeRequest.getSalary() < 18000) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "Salary must be above 18000 to meet the minimum wage.");
        }

        if (deliveryEmployeeRequest.getBankAccountNumber().length() > 34) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "Bank account number greater than 34 characters.");
        }

        if (deliveryEmployeeRequest.getNationalInsuranceNumber().length() > 9) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "National Insurance number greater than 9 characters.");
        }


        if (deliveryEmployeeRequest.getName().isEmpty() ||
                deliveryEmployeeRequest.getName() == null) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "Name greater is blank. Please enter a name.");
        }

        if (deliveryEmployeeRequest.getSalary() == null) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "Name greater is blank. Please enter a name.");
        }


        if (deliveryEmployeeRequest.getBankAccountNumber().isEmpty() ||
                deliveryEmployeeRequest.getBankAccountNumber() == null) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "Bank account number is blank. Please enter a bank account number.");
        }

        if (deliveryEmployeeRequest.getNationalInsuranceNumber().isEmpty() ||
                deliveryEmployeeRequest.getNationalInsuranceNumber() == null) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "National Insurance number is blank. Please enter a national insurance number.");
        }
    }
}
