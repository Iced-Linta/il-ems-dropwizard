CREATE TABLE deliveryEmployee
(
    deliveryEmployeeId      INT NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(50),
    salary                  DECIMAL(7, 2),
    bankAccountNumber       VARCHAR(34),
    nationalInsuranceNumber CHAR(9),
    PRIMARY KEY (deliveryEmployeeId)
);