package org.example.daos;

import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.models.UserRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthDao {
    public User getUser(final LoginRequest loginRequest) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM `User` where username = ? and password = ?;";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, loginRequest.getUsername());
            statement.setString(2, loginRequest.getPassword());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("roleId")
                );
            }
        }
        return null;
    }

    public int createUser(final UserRequest userRequest)
            throws SQLException {
        try (Connection c = DatabaseConnector.getConnection()) {

            String insertStatement =
                    "INSERT INTO `User` (username, password) VALUES (?,?);";
            PreparedStatement statement = c.prepareStatement(insertStatement,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, userRequest.getUsername());
            statement.setString(2, userRequest.getPassword());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return -1;

        }
    }
}
