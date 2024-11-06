package org.example.daos;

import org.example.models.SalesEmployee;
import org.example.models.SalesEmployeeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {
    private final int firstNameIndex = 1;
    private final int lastNameIndex = 2;
    private final int salaryIndex = 3;
    private final int bankAccountIndex = 4;
    private final int commissionRateIndex = 5;
    private final int nationalInsuranceNumberIndex = 6;
    private final int updateIdIndex = 7;

    public List<SalesEmployee> getAllSalesEmployees() throws SQLException {
        List<SalesEmployee> salesEmployees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM salesEmployees;");

            while (resultSet.next()) {
                SalesEmployee salesEmployee = new SalesEmployee(
                        resultSet.getInt("salesEmployeeId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bankAccountNumber"),
                        resultSet.getFloat("commissionRate"),
                        resultSet.getString("nationalInsuranceNumber")
                );
                salesEmployees.add(salesEmployee);
            }
        }
        return salesEmployees;
    }

    public SalesEmployee getSalesEmployeeById(final int id)
            throws SQLException {

        try (Connection connection = DatabaseConnector.getConnection()) {

            String query = "SELECT * FROM salesEmployees "
                    + "WHERE salesEmployeeId = ?;";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SalesEmployee salesEmployee = new SalesEmployee(
                        resultSet.getInt("salesEmployeeId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bankAccountNumber"),
                        resultSet.getFloat("commissionRate"),
                        resultSet.getString("nationalInsuranceNumber")
                );

                return salesEmployee;
            }

        }

        return null;
    }

    public int createSalesEmployee(
            final SalesEmployeeRequest salesEmployeeRequest
    )
            throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String insertStatement = "INSERT INTO salesEmployees"
                + " (firstName,lastName,salary,bankAccountNumber,"
                + " commissionRate,nationalInsuranceNumber)"
                + " VALUES(?,?,?,?,?,?);";
        PreparedStatement st = c.prepareStatement(
                insertStatement, Statement.RETURN_GENERATED_KEYS);
        st.setString(firstNameIndex,
                salesEmployeeRequest.getFirstName());
        st.setString(lastNameIndex, salesEmployeeRequest.getLastName());
        st.setDouble(salaryIndex, salesEmployeeRequest.getSalary());
        st.setString(bankAccountIndex,
                salesEmployeeRequest.getBankAccountNo());
        st.setDouble(commissionRateIndex,
                salesEmployeeRequest.getCommissionRate());
        st.setString(nationalInsuranceNumberIndex,
                salesEmployeeRequest.getNationalInsuranceNo());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public void updateSalesEmployee(
            final int id,
            final SalesEmployeeRequest salesEmployeeRequest)
            throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String updateStatement = "UPDATE salesEmployees "
                + "SET firstName = ?, lastName = ? "
                + ",salary =?, bankAccountNumber =?, commissionRate=?, nationalInsuranceNumber=? " +
                "WHERE salesEmployeeId = ?;";
        PreparedStatement st = c.prepareStatement(updateStatement);
        st.setString(firstNameIndex, salesEmployeeRequest.getFirstName());
        st.setString(lastNameIndex, salesEmployeeRequest.getLastName());
        st.setDouble(salaryIndex, salesEmployeeRequest.getSalary());
        st.setString(bankAccountIndex,
                salesEmployeeRequest.getBankAccountNo());
        st.setDouble(commissionRateIndex,
                salesEmployeeRequest.getCommissionRate());
        st.setString(nationalInsuranceNumberIndex,
                salesEmployeeRequest.getNationalInsuranceNo());
        st.setInt(updateIdIndex, id);

        st.executeUpdate();
    }

    public void deleteSalesEmployee(final int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatement = "DELETE FROM salesEmployees"
                + " WHERE salesEmployeeId = ?;";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1, id);
        st.executeUpdate();
    }
}
