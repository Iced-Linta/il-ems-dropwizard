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

    public List<SalesEmployee> getSalesEmployees() throws SQLException {
        List<SalesEmployee> salesEmployees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM salesEmployees;");

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
        try (Connection c = DatabaseConnector.getConnection()) {
            String insertStatement = "INSERT INTO salesEmployees"
                    + " (firstName,lastName,salary,bankAccountNumber,"
                    + " commissionRate,nationalInsuranceNumber)"
                    + " VALUES(?,?,?,?,?,?);";
            PreparedStatement st = c.prepareStatement(
                    insertStatement, Statement.RETURN_GENERATED_KEYS);
            st.setString(Indexes.FIRST_NAME.getIndex(),
                    salesEmployeeRequest.getFirstName());
            st.setString(Indexes.LAST_NAME.getIndex(),
                    salesEmployeeRequest.getLastName());
            st.setDouble(Indexes.SALARY.getIndex(),
                    salesEmployeeRequest.getSalary());
            st.setString(Indexes.BANK_ACCOUNT.getIndex(),
                    salesEmployeeRequest.getBankAccountNo());
            st.setDouble(Indexes.COMMISSION_RATE.getIndex(),
                    salesEmployeeRequest.getCommissionRate());
            st.setString(Indexes.NATIONAL_INSURANCE_NUMBER.getIndex(),
                    salesEmployeeRequest.getNationalInsuranceNo());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return -1;
    }

    public void updateSalesEmployee(
            final int id,
            final SalesEmployeeRequest salesEmployeeRequest)
            throws SQLException {
        try (Connection c = DatabaseConnector.getConnection()) {
            String updateStatement = "UPDATE salesEmployees "
                    + "SET firstName = ?," +
                    "lastName = ? " +
                    ",salary =?," +
                    "bankAccountNumber =?," +
                    "commissionRate=?," +
                    "nationalInsuranceNumber=?" +
                    "WHERE salesEmployeeId = ?;";
            PreparedStatement st = c.prepareStatement(updateStatement);
            st.setString(Indexes.FIRST_NAME.getIndex(),
                    salesEmployeeRequest.getFirstName());
            st.setString(Indexes.LAST_NAME.getIndex(),
                    salesEmployeeRequest.getLastName());
            st.setDouble(Indexes.SALARY.getIndex(),
                    salesEmployeeRequest.getSalary());
            st.setString(Indexes.BANK_ACCOUNT.getIndex(),
                    salesEmployeeRequest.getBankAccountNo());
            st.setDouble(Indexes.COMMISSION_RATE.getIndex(),
                    salesEmployeeRequest.getCommissionRate());
            st.setString(Indexes.NATIONAL_INSURANCE_NUMBER.getIndex(),
                    salesEmployeeRequest.getNationalInsuranceNo());
            st.setInt(Indexes.UPDATE_ID.getIndex(), id);

            st.executeUpdate();

        }
    }

    public void deleteSalesEmployee(final int id) throws SQLException {
        try (Connection c = DatabaseConnector.getConnection()) {
            String deleteStatement = "DELETE FROM salesEmployees"
                    + " WHERE salesEmployeeId = ?;";
            PreparedStatement st = c.prepareStatement(deleteStatement);
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    private enum Indexes {
        FIRST_NAME(1),
        LAST_NAME(2),
        SALARY(3),
        BANK_ACCOUNT(4),
        COMMISSION_RATE(5),
        NATIONAL_INSURANCE_NUMBER(6),
        UPDATE_ID(7);

        private final int index;

        Indexes(final int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }
    }
}
