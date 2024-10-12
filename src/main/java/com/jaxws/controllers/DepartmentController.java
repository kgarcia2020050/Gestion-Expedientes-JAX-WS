package com.jaxws.controllers;

import com.jaxws.dtos.DepartmentDto;
import com.jaxws.dtos.Request;
import com.jaxws.dtos.Response;
import com.jaxws.services.DepartmentService;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@WebService(name = "DepartmentController", serviceName = "DepartmentController")
@XmlRootElement(name = "DEPARTMENT_RESPONSE")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController() {
        this.departmentService = new DepartmentService();
    }

    private static final String ERROR_MESSAGE = "Missing or invalid Authorization header";


    @WebMethod(operationName = "GET_DEPARTMENTS", action = "GET_DEPARTMENTS")
    @XmlElement(name = "DEPARTMENT")
    public Response getDepartments(Request request) {

        if (!request.getAuthToken().isEmpty() && request.validateToken()) {
            return departmentService.getDepartments();

        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }


    }

    @WebMethod(operationName = "CREATE_DEPARTMENT", action = "CREATE_DEPARTMENT")
    public Response createDepartment(@XmlElement(name = "DEPARTMENT") DepartmentDto department) {

        if (!department.getAuthToken().isEmpty() && department.validateToken()) {
            return departmentService.createDepartment(department);

        } else {

            throw new SecurityException(ERROR_MESSAGE);
        }

    }

    @WebMethod(operationName = "UPDATE_DEPARTMENT", action = "UPDATE_DEPARTMENT")
    public Response updateDepartment(@XmlElement(name = "DEPARTMENT") DepartmentDto department, @XmlElement(name = "ID") int id) {

        if (!department.getAuthToken().isEmpty() && department.validateToken()) {
            return departmentService.updateDepartment(department, id);
        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }

    }

    @WebMethod(operationName = "DELETE_DEPARTMENT", action = "DELETE_DEPARTMENT")
    public Response deleteDepartment(@XmlElement(name = "ID") int id, Request request) {

        if (!request.getAuthToken().isEmpty() && request.validateToken()) {
            return departmentService.deleteDepartment(id);
        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }
    }

}
