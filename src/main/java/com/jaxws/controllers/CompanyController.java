package com.jaxws.controllers;

import com.jaxws.dtos.CompanyDto;
import com.jaxws.dtos.Response;
import com.jaxws.services.CompanyService;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.SQLException;

@WebService(name = "CompanyController", serviceName = "CompanyController")
@XmlRootElement(name = "COMPANY_RESPONSE")
@HandlerChain(file = "handler-chain.xml")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController() {
        this.companyService = new CompanyService();
    }


    @WebMethod(operationName = "GET_COMPANIES", action = "GET_COMPANIES")
    @XmlElement(name = "COMPANY")
    public Response getCompanies() {
        return companyService.getCompanies();
    }

    @WebMethod(operationName = "CREATE_COMPANY", action = "CREATE_COMPANY")
    public Response registerCompany(@WebParam(name = "COMPANY") CompanyDto company) {
        return companyService.createCompany(company);
    }

    @WebMethod(operationName = "UPDATE_COMPANY", action = "UPDATE_COMPANY")
    public Response updateCompany(@WebParam(name = "COMPANY") CompanyDto company, @WebParam(name = "ID") int id)  {
        return companyService.updateCompany(company, id);
    }

    @WebMethod(operationName = "DELETE_COMPANY", action = "DELETE_COMPANY")
    public Response deleteCompany(@WebParam(name = "ID") int id)  {
        return companyService.deleteCompany(id);
    }

}
