package org.example.responses;

public class DeliveryEmployeeResponse {
    private int deliveryEmployeeId;
    private String name;
    private Double salary;
    private String bankAccountNumber;
    private String nationalInsuranceNumber;

    public DeliveryEmployeeResponse(final int deliveryEmployeeId,
                                    final String name,
                                    final Double salary,
                                    final String bankAccountNumber,
                                    final String nationalInsuranceNumber) {
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.name = name;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
    }

    public void setDeliveryEmployeeId(int deliveryEmployeeId) {
        this.deliveryEmployeeId = deliveryEmployeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(final Double salary) {
        this.salary = salary;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(final String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(
            final String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }
}
