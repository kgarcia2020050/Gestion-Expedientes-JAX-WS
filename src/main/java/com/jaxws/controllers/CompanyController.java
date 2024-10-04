package com.jaxws.controllers;

import com.jaxws.dtos.Response;
import com.jaxws.services.CompanyService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.SQLException;

@WebService(name = "CompanyController", serviceName = "CompanyController")
@XmlRootElement(name = "COMPANY_RESPONSE")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController() {
        this.companyService = new CompanyService();
    }


    @WebMethod(operationName = "GET_COMPANIES", action = "GET_COMPANIES")
    @XmlElement(name = "COMPANY")
    public Response getCompanies() throws SQLException {
        return companyService.getCompanies();
    }

}
