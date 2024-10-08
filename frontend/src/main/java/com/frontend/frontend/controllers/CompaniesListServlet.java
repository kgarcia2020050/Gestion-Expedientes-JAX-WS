package com.frontend.frontend.controllers;

import com.frontend.frontend.wsdl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.handler.MessageContext;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/companies")
public class CompaniesListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CompanyController_Service companyService = new CompanyController_Service();

    private final String LIST_COMPANIES_JSP = "/components/private/admin/companies/companiesList.jsp";

    private final String ADD_COMPANIES_JSP = "/components/private/admin/companies/companiesAdd.jsp";

    CompanyController port = companyService.getCompanyControllerPort();


    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(request.getContextPath() + path).forward(request, response);
    }

    private void addAuthorization(String authToken) {
        if (authToken != null) {
            BindingProvider bindingProvider = (BindingProvider) port;
            Map<String, Object> requestContext = bindingProvider.getRequestContext();
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://DESKTOP-5QHEAJA:8080/jaxws-1.0-SNAPSHOT/CompanyController?wsdl");
            requestContext.put("javax.xml.ws.client.connectionTimeout", 10000);
            requestContext.put("javax.xml.ws.client.receiveTimeout", 10000);

            Map<String, List<String>> headers = (Map<String, List<String>>) requestContext.get(MessageContext.HTTP_REQUEST_HEADERS);
            if (headers == null) {
                headers = new java.util.HashMap<>();
                requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
            }

            headers.put("Authorization", List.of(authToken));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "edit":
                editCompany(request, response);
                break;
            default:
                System.out.println("ACCION NO VALIDA: " + action);

                request.setAttribute("error", "Acción no válida");
                redirect(request, response, LIST_COMPANIES_JSP);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "get":
                listCompanies(request, response);
                break;
            case "post":
                addCompany(request, response);
                break;
            default:
                System.out.println("ACCION NO VALIDA: " + action);
                request.setAttribute("error", "Acción no válida");
                redirect(request, response, LIST_COMPANIES_JSP);
                break;
        }
    }


    private void listCompanies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("LISTANDO COMPANIAS");

            String authToken = (String) request.getAttribute("token");

            System.out.println("TOKEN: " + authToken);


            addAuthorization(authToken);
            Response loginSuccessful = port.getCOMPANIES();

            if (loginSuccessful.isSUCCESS()) {
                List<Company> companies = loginSuccessful.getCOMPANIES();
                System.out.println(companies.toString());
                for (Company company : companies) {
                    System.out.println(company.toString());
                }

            } else {
                request.setAttribute("error", loginSuccessful.getMESSAGE());
                System.out.println("ERROR: " + loginSuccessful.getMESSAGE());
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            request.setAttribute("error", "Error al obtener las compañías: " + e.getMessage());
        }
        redirect(request, response, LIST_COMPANIES_JSP);
    }


    private void addCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AGREGANDO COMPANIA");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String actividadEconomica = request.getParameter("actividadEconomica");


        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(nombre);
        companyDto.setAddress(direccion);
        companyDto.setPhone(telefono);
        companyDto.setEmail(email);
        companyDto.setEconomicActivity(actividadEconomica);

        addAuthorization((String) request.getAttribute("token"));

        Response createUserResponse = port.createCOMPANY(companyDto);

        if (createUserResponse.isSUCCESS()) {
            listCompanies(request, response);
        } else {
            request.setAttribute("error", createUserResponse.getMESSAGE());
            redirect(request, response, LIST_COMPANIES_JSP);
        }
    }

    private void editCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EDITANDO COMPANIA");
        String id = request.getParameter("id");

        if (id != null) {
            try {
                Response loginSuccessful = port.getCOMPANIES();

                if (loginSuccessful.isSUCCESS()) {
                    List<Company> companies = loginSuccessful.getCOMPANIES();

                    for (Company company : companies) {
                        if (company.getID() == Integer.parseInt(id)) {
                            request.setAttribute("company", company);
                            break;
                        }
                    }

                } else {
                    request.setAttribute("error", "Compañía no encontrada");
                    redirect(request, response, LIST_COMPANIES_JSP);
                }
            } catch (Exception e) {
                request.setAttribute("error", "Error al editar la compañía: " + e.getMessage());
                redirect(request, response, LIST_COMPANIES_JSP);
            }
        } else {
            request.setAttribute("error", "ID de compañía no proporcionado");
            redirect(request, response, ADD_COMPANIES_JSP);
        }
    }


}
