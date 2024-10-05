package com.frontend.frontend.controllers;

import com.frontend.frontend.models.Company;
import com.frontend.frontend.wsdl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/companiesList")
public class CompaniesListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CompanyController_Service companyService = new CompanyController_Service();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        try {
            CompanyController port = companyService.getCompanyControllerPort();
            Response loginSuccessful = port.getCOMPANIES();

            if (loginSuccessful.isSUCCESS()) {
                List<Company> companies = (List<Company>) loginSuccessful.getDATA();
                request.setAttribute("companies", companies);
            } else {
                request.setAttribute("error", companyResponse.getMessage());
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener las compañías: " + e.getMessage());
        }
        request.getRequestDispatcher("companies_list.jsp").forward(request, response);

    }
}

