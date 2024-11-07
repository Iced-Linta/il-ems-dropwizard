package org.example.daos;

import org.example.models.DeliveryEmployee;
import org.example.models.SalesEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeProjectDao {

    public List<DeliveryEmployee> getDeliveryEmployees() throws SQLException {
        List<DeliveryEmployee> deliveryEmployees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM deliveryEmployees;");

            ResultSet resultSet = statement.executeQuery();

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
}
