package com.jaxws.controllers;

import com.jaxws.dtos.CompanyDto;
import com.jaxws.dtos.Request;
import com.jaxws.dtos.Response;
import com.jaxws.services.CompanyService;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@WebService(name = "CompanyController", serviceName = "CompanyController")
@XmlRootElement(name = "COMPANY_RESPONSE")
public class CompanyController {

    private final CompanyService companyService;
    private static final String ERROR_MESSAGE = "Missing or invalid Authorization header";

    public CompanyController() {
        this.companyService = new CompanyService();
    }


    @WebMethod(operationName = "GET_COMPANIES", action = "GET_COMPANIES")
    @XmlElement(name = "COMPANY")
    public Response getCompanies(Request request) {

        if (!request.getAuthToken().isEmpty() && request.validateToken()) {
            return companyService.getCompanies();

        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }

    }

    @WebMethod(operationName = "CREATE_COMPANY", action = "CREATE_COMPANY")
    public Response registerCompany(@WebParam(name = "COMPANY") CompanyDto company) {
        if (!company.getAuthToken().isEmpty() && company.validateToken()) {
            return companyService.createCompany(company);

        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }
    }

    @WebMethod(operationName = "UPDATE_COMPANY", action = "UPDATE_COMPANY")
    public Response updateCompany(@WebParam(name = "COMPANY") CompanyDto company, @WebParam(name = "ID") int id) {

        if (!company.getAuthToken().isEmpty() && company.validateToken()) {
            return companyService.updateCompany(company, id);
        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }

    }

    @WebMethod(operationName = "DELETE_COMPANY", action = "DELETE_COMPANY")
    public Response deleteCompany(@WebParam(name = "ID") int id, Request request) {

        if (!request.getAuthToken().isEmpty() && request.validateToken()) {
            return companyService.deleteCompany(id);
        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }

    }

}
