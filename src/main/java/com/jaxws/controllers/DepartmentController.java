package com.jaxws.controllers;

import com.jaxws.dtos.DepartmentDto;
import com.jaxws.dtos.Response;
import com.jaxws.services.DepartmentService;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@WebService(name = "DepartmentController", serviceName = "DepartmentController")
@XmlRootElement(name = "DEPARTMENT_RESPONSE")
//@HandlerChain(file = "handler-chain.xml")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController() {
        this.departmentService = new DepartmentService();
    }

    @WebMethod(operationName = "GET_DEPARTMENTS", action = "GET_DEPARTMENTS")
    @XmlElement(name = "DEPARTMENT")
    public Response getDepartments()  {
        return departmentService.getDepartments();
    }

    @WebMethod(operationName = "CREATE_DEPARTMENT", action = "CREATE_DEPARTMENT")
    public Response createDepartment(@XmlElement(name = "DEPARTMENT") DepartmentDto department) {
        return departmentService.createDepartment(department);
    }

    @WebMethod(operationName = "UPDATE_DEPARTMENT", action = "UPDATE_DEPARTMENT")
    public Response updateDepartment(@XmlElement(name = "DEPARTMENT") DepartmentDto department, @XmlElement(name = "ID") int id)  {
        return departmentService.updateDepartment(department, id);
    }

    @WebMethod(operationName = "DELETE_DEPARTMENT", action = "DELETE_DEPARTMENT")
    public Response deleteDepartment(@XmlElement(name = "ID") int id)  {
        return departmentService.deleteDepartment(id);
    }

}
