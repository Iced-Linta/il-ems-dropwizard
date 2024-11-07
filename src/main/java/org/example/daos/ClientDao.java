package org.example.daos;

import org.example.models.Client;
import org.example.models.ClientRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    public List<Client> getAllClients() throws
            SQLException {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM client;");

            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("clientId"),
                        resultSet.getString("name")
                );

                clients.add(client);
            }
        }
        return clients;
    }

    public Client getClientById(final int id)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "SELECT * FROM client WHERE clientId = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Client(
                        resultSet.getInt("clientId"),
                        resultSet.getString("name")
                );
            }
        }
        return null;
    }

    public int createClient(final ClientRequest client)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {

            String insertStatement =
                    "INSERT INTO client (name) VALUES (?)";
            PreparedStatement statement =
                    connection.prepareStatement(insertStatement,
                            Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, client.getName());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return -1;
    }

    public void updateClient(final int id,
                             final ClientRequest clientRequest)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {

            String updateStatement =
                    "UPDATE client SET name = ? WHERE clientId = ?";
            PreparedStatement statement =
                    connection.prepareStatement(updateStatement);

            statement.setString(1, clientRequest.getName());
            statement.setInt(2, id);

            statement.executeUpdate();
        }
    }

    public void deleteClient(final int id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {

            String deleteStatement =
                    "DELETE FROM client WHERE clientId = ?";
            PreparedStatement statement =
                    connection.prepareStatement(deleteStatement);

            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }
}
