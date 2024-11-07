package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesEmployeeRequest {
    private String firstName;
    private String lastName;
    private double salary;
    private String bankAccountNo;
    private float commissionRate;
    private String nationalInsuranceNo;

    public SalesEmployeeRequest(
            final @JsonProperty("firstName") String firstName,
            final @JsonProperty("lastName") String lastName,
            final @JsonProperty("salary") double salary,
            final @JsonProperty("bankAccountNo") String bankAccountNo,
            final @JsonProperty("commissionRate") float commissionRate,
            final @JsonProperty("nationalInsuranceNo") String nationalInsuranceNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.bankAccountNo = bankAccountNo;
        this.commissionRate = commissionRate;
        this.nationalInsuranceNo = nationalInsuranceNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public String getNationalInsuranceNo() {
        return nationalInsuranceNo;
    }
}
