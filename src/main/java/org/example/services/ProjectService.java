package org.example.services;

import org.example.daos.ProjectDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.models.Project;
import org.example.models.ProjectRequest;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    private final ProjectDao projectDao;
    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public List<Project> getProjects()
            throws SQLException {
        return projectDao.getProjects();
    }

    public Project getProject(int id) throws SQLException,
            DoesNotExistException {
        Project salesEmployee = projectDao.getProjectById(id);
        if (salesEmployee == null) { throw new DoesNotExistException(Entity.PROJECT); }
        return salesEmployee;
    }

    public int createProject(ProjectRequest projectRequest)
            throws SQLException, FailedToCreateException {
        int salesEmployeeId = projectDao.createProject(projectRequest);
        if (salesEmployeeId == -1) { throw new FailedToCreateException(Entity.PROJECT); }
        return salesEmployeeId;
    }

    public void deleteProject(int id) throws SQLException, DoesNotExistException {
        Project salesEmployee = projectDao.getProjectById(id);
        if (salesEmployee == null) { throw new DoesNotExistException(Entity.PROJECT); }
        projectDao.deleteProject(id);
    }

    public void updateProject(ProjectRequest projectRequest, int id) throws SQLException, DoesNotExistException {
        Project salesEmployee = projectDao.getProjectById(id);
        if (salesEmployee == null) { throw new DoesNotExistException(Entity.PROJECT); }
        projectDao.updateSalesEmployee(id, projectRequest);
    }
}
