package org.example.daos;

import org.example.models.DeliveryEmployee;
import org.example.models.DeliveryEmployeeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {
    public List<DeliveryEmployee> getAllDeliveryEmployees() throws
            SQLException {
        List<DeliveryEmployee> deliveryEmployees = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM deliveryEmployee;");

            while (resultSet.next()) {
                DeliveryEmployee deliveryEmployee = new DeliveryEmployee(
                        resultSet.getInt("deliveryEmployeeID"),
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bankAccountNumber"),
                        resultSet.getString("nationalInsuranceNumber")
                );

                deliveryEmployees.add(deliveryEmployee);
            }
        }
        return deliveryEmployees;
    }

    public DeliveryEmployee getDeliveryEmployeeById(int id)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "SELECT * FROM deliveryEmployee WHERE deliveryEmployeeId = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new DeliveryEmployee(
                        resultSet.getInt("deliveryEmployeeID"),
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bankAccountNumber"),
                        resultSet.getString("nationalInsuranceNumber")
                );
            }
        }
        return null;
    }

    public int createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployee)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {

            String insertStatement =
                    "INSERT INTO deliveryEmployee (name, salary, bankAccountNumber, nationalInsuranceNumber) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertStatement,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, deliveryEmployee.getName());
            statement.setDouble(2, deliveryEmployee.getSalary());
            statement.setString(3, deliveryEmployee.getBankAccountNumber());
            statement.setString(4,
                    deliveryEmployee.getNationalInsuranceNumber());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
            return -1;
    }

    public void updateDeliveryEmployee(final int id,
                                       final DeliveryEmployeeRequest deliveryEmployeeRequest)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {

            String updateStatement =
                    "UPDATE deliveryEmployee SET name = ?, salary = ?, bankAccountNumber = ?, nationalInsuranceNumber = ? WHERE deliveryEmployeeId = ?";
            PreparedStatement statement =
                    connection.prepareStatement(updateStatement);

            statement.setString(1, deliveryEmployeeRequest.getName());
            statement.setDouble(2, deliveryEmployeeRequest.getSalary());
            statement.setString(3,
                    deliveryEmployeeRequest.getBankAccountNumber());
            statement.setString(4,
                    deliveryEmployeeRequest.getNationalInsuranceNumber());
            statement.setInt(5, id);

            statement.executeUpdate();
        }
    }

    public void deleteDeliveryEmployee(final int id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {

            String deleteStatement =
                    "DELETE FROM deliveryEmployee WHERE deliveryEmployeeId = ?";
            PreparedStatement statement =
                    connection.prepareStatement(deleteStatement);

            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }
}
