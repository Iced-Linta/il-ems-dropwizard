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

    public void checkProjectExists(Project project) throws DoesNotExistException {
        if (project == null) { throw new DoesNotExistException(Entity.PROJECT); }
    }

    public List<Project> getProjects()
            throws SQLException {
        return projectDao.getProjects();
    }

    public Project getProject(int id) throws SQLException,
            DoesNotExistException {
        Project project = projectDao.getProjectById(id);
        if (project == null) { throw new DoesNotExistException(Entity.PROJECT); }
        return project;
    }

    public int createProject(ProjectRequest projectRequest)
            throws SQLException, FailedToCreateException {
        int project = projectDao.createProject(projectRequest);
        if (project == -1) { throw new FailedToCreateException(Entity.PROJECT); }
        return project;
    }

    public void deleteProject(int id) throws SQLException, DoesNotExistException {
        checkProjectExists(projectDao.getProjectById(id));
        projectDao.deleteProject(id);
    }

    public void updateProject(ProjectRequest projectRequest, int id) throws SQLException, DoesNotExistException {
        checkProjectExists(projectDao.getProjectById(id));
        projectDao.updateProject(id, projectRequest);
    }
}
