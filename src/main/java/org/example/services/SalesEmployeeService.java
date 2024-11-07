package org.example.services;

import org.example.daos.SalesEmployeeDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.models.SalesEmployee;
import org.example.models.SalesEmployeeRequest;

import java.sql.SQLException;
import java.util.List;

public class SalesEmployeeService {
    private final SalesEmployeeDao salesEmployeeDao;

    public SalesEmployeeService(SalesEmployeeDao salesEmployeeDao) {
        this.salesEmployeeDao = salesEmployeeDao;
    }

    public List<SalesEmployee> getSalesEmployees()
            throws SQLException {
        return salesEmployeeDao.getSalesEmployees();
    }

    public SalesEmployee getSalesEmployee(int id)
            throws SQLException, DoesNotExistException {
        SalesEmployee salesEmployee = salesEmployeeDao.getSalesEmployeeById(id);
        if (salesEmployee == null) {
            throw new DoesNotExistException(Entity.SALES_EMPLOYEE);
        }
        return salesEmployee;
    }

    public int createSalesEmployee(SalesEmployeeRequest salesEmployeeRequest)
            throws SQLException, FailedToCreateException {
        int salesEmployeeId =
                salesEmployeeDao.createSalesEmployee(salesEmployeeRequest);
        if (salesEmployeeId == -1) {
            throw new FailedToCreateException(Entity.SALES_EMPLOYEE);
        }
        return salesEmployeeId;
    }

    public void deleteSalesEmployee(int id)
            throws SQLException, DoesNotExistException {
        SalesEmployee salesEmployee = salesEmployeeDao.getSalesEmployeeById(id);
        if (salesEmployee == null) {
            throw new DoesNotExistException(Entity.SALES_EMPLOYEE);
        }
        salesEmployeeDao.deleteSalesEmployee(id);
    }

    public void updateSalesEmployee(SalesEmployeeRequest salesEmployeeRequest,
                                    int id)
            throws SQLException, DoesNotExistException {
        SalesEmployee salesEmployee = salesEmployeeDao.getSalesEmployeeById(id);
        if (salesEmployee == null) {
            throw new DoesNotExistException(Entity.SALES_EMPLOYEE);
        }
        salesEmployeeDao.updateSalesEmployee(id, salesEmployeeRequest);
    }
}
