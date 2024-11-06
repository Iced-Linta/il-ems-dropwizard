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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getNationalInsuranceNo() {
        return nationalInsuranceNo;
    }

    public void setNationalInsuranceNo(String nationalInsuranceNo) {
        this.nationalInsuranceNo = nationalInsuranceNo;
    }
}
