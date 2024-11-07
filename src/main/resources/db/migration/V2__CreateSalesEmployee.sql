CREATE TABLE salesEmployees (
    salesEmployeeId INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    bankAccountNumber VARCHAR(34) NOT NULL,
    commissionRate DECIMAL(3,2) NOT NULL,
    nationalInsuranceNumber CHAR(9),
    PRIMARY KEY(salesEmployeeId)
);
