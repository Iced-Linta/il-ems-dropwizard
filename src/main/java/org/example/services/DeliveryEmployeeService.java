package org.example.services;

import org.example.daos.DeliveryEmployeeDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.mappers.DeliveryEmployeeMapper;
import org.example.models.DeliveryEmployee;
import org.example.models.DeliveryEmployeeRequest;
import org.example.responses.DeliveryEmployeeResponse;
import org.example.validators.DeliveryEmployeeValidator;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {
    DeliveryEmployeeDao deliveryEmployeeDao;
    DeliveryEmployeeValidator deliveryEmployeeValidator;

    public DeliveryEmployeeService(
            final DeliveryEmployeeDao deliveryEmployeeDao,
            final DeliveryEmployeeValidator deliveryEmployeeValidator) {
        this.deliveryEmployeeDao = deliveryEmployeeDao;
        this.deliveryEmployeeValidator = deliveryEmployeeValidator;
    }

    public List<DeliveryEmployeeResponse> getAllDeliveryEmployees()
            throws SQLException {
        return DeliveryEmployeeMapper.mapDeliveryEmployeeListToDeliveryEmployeeResponseList(
                deliveryEmployeeDao.getAllDeliveryEmployees());
    }

    public DeliveryEmployeeResponse getDeliveryEmployeeById(final int id)
            throws SQLException, DoesNotExistException {
        DeliveryEmployee deliveryEmployee =
                deliveryEmployeeDao.getDeliveryEmployeeById(id);

        if (deliveryEmployee == null) {
            throw new DoesNotExistException(Entity.DELIVERY_EMPLOYEE);
        }

        return DeliveryEmployeeMapper.mapDeliveryEmployeeToDeliveryEmployeeResponse(
                deliveryEmployee);
    }

    public int createDeliveryEmployee(
            final DeliveryEmployeeRequest deliveryEmployeeRequest)
            throws FailedToCreateException, SQLException, InvalidException {
        deliveryEmployeeValidator.validateDeliveryEmployee(
                deliveryEmployeeRequest);

        final int id = deliveryEmployeeDao.createDeliveryEmployee(
                deliveryEmployeeRequest);

        if (id == -1) {
            throw new FailedToCreateException(Entity.DELIVERY_EMPLOYEE);
        }

        return id;
    }

    public void updateDeliveryEmployee(final int id,
                                       final DeliveryEmployeeRequest deliveryEmployeeRequest)
            throws InvalidException, SQLException, DoesNotExistException {
        deliveryEmployeeValidator.validateDeliveryEmployee(
                deliveryEmployeeRequest);
        DeliveryEmployee deliveryEmployeeToUpdate =
                deliveryEmployeeDao.getDeliveryEmployeeById(id);

        if (deliveryEmployeeToUpdate == null) {
            throw new DoesNotExistException(Entity.DELIVERY_EMPLOYEE);
        }

        deliveryEmployeeDao.updateDeliveryEmployee(id, deliveryEmployeeRequest);
    }

    public void deleteDeliveryEmployee(final int id) throws SQLException,
            DoesNotExistException {
        DeliveryEmployee deliveryEmployeeToDelete =
                deliveryEmployeeDao.getDeliveryEmployeeById(id);
        if (deliveryEmployeeToDelete == null) {
            throw new DoesNotExistException(Entity.DELIVERY_EMPLOYEE);
        }

        deliveryEmployeeDao.deleteDeliveryEmployee(id);
    }
}