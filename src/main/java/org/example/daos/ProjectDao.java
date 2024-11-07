package org.example.daos;

import org.example.models.DeliveryEmployee;
import org.example.models.Project;
import org.example.models.ProjectRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {
    private enum Indexes {
        name(1),
        value(2),
        techLeadId(3),
        clientId(4),
        salesEmployeeId(5),
        startDate(6),
        finishDate(7),
        updateId(8);

        private final int index;
        Indexes(final int index) {
            this.index = index;
        }
        public int getIndex() {
            return this.index;
        }
    }

    public List<Project> getProjects() throws
            SQLException {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM project;");

            while (resultSet.next()) {
                Project project = new Project(
                        resultSet.getInt("projectId"),
                        resultSet.getString("name"),
                        resultSet.getFloat("value"),
                        resultSet.getInt("techLeadId"),
                        resultSet.getInt("clientId"),
                        resultSet.getInt("salesEmployeeId"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("finishDate")
                );
                projects.add(project);
            }
        }
        return projects;
    }
    public Project getProjectById(int id)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "SELECT * FROM project WHERE projectId = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Project(
                        resultSet.getInt("ProjectId"),
                        resultSet.getString("name"),
                        resultSet.getFloat("value"),
                        resultSet.getInt("techLeadId"),
                        resultSet.getInt("clientId"),
                        resultSet.getInt("salesEmployeeId"),
                        resultSet.getDate("startDate"),
                        resultSet.getDate("finishDate")
                );
            }
        }
        return null;
    }
    public int createProject(
            final ProjectRequest projectRequest
    )
            throws SQLException {
        try (Connection c = DatabaseConnector.getConnection()) {
            String insertStatement = "INSERT INTO project"
                    + " (name,value,techLeadId,"
                    + " clientId,salesEmployeeId," +
                    "startDate, finishDate)"
                    + " VALUES(?,?,?,?,?,?,?);";
            PreparedStatement st = c.prepareStatement(
                    insertStatement, Statement.RETURN_GENERATED_KEYS);
            st.setString(Indexes.name.getIndex(),
                    projectRequest.getName());
            st.setFloat(Indexes.value.getIndex(),
                    projectRequest.getValue());
            st.setInt(Indexes.techLeadId.getIndex(),
                    projectRequest.getTechLeadId());
            st.setInt(Indexes.clientId.getIndex(),
                    projectRequest.getClientId());
            st.setInt(Indexes.salesEmployeeId.getIndex(),
                    projectRequest.getSalesEmployeeId());
            st.setDate(Indexes.startDate.getIndex(),
                    projectRequest.getStartDate());
            st.setDate(Indexes.finishDate.getIndex(),
                    projectRequest.getFinishDate());

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return -1;
    }

    public void updateProject(
            final int id,
            final ProjectRequest projectRequest)
            throws SQLException {
        try (Connection c = DatabaseConnector.getConnection()) {
            String updateStatement = "UPDATE project " +
                    "SET name = ?," +
                    "value =?," +
                    "techLeadId =?," +
                    "clientId=?," +
                    "salesEmployeeId =?," +
                    "startDate=?," +
                    "finishDate=?," +
                    "WHERE projectId = ?;";
            PreparedStatement st = c.prepareStatement(updateStatement);
            st.setString(Indexes.name.getIndex(),
                    projectRequest.getName());
            st.setFloat(Indexes.value.getIndex(),
                    projectRequest.getValue());
            st.setInt(Indexes.techLeadId.getIndex(),
                    projectRequest.getTechLeadId());
            st.setInt(Indexes.clientId.getIndex(),
                    projectRequest.getClientId());
            st.setInt(Indexes.salesEmployeeId.getIndex(),
                    projectRequest.getSalesEmployeeId());
            st.setDate(Indexes.startDate.getIndex(),
                    projectRequest.getStartDate());
            st.setDate(Indexes.finishDate.getIndex(),
                    projectRequest.getFinishDate());
            st.setInt(Indexes.updateId.getIndex(), id);

            st.executeUpdate();

        }
    }

    public void deleteProject(final int id) throws SQLException {
        try (Connection c = DatabaseConnector.getConnection()) {
            String deleteStatement = "DELETE FROM project"
                    + " WHERE projectId = ?;";
            PreparedStatement st = c.prepareStatement(deleteStatement);
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}

