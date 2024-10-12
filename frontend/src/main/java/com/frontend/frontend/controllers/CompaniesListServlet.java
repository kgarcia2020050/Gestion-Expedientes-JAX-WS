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

    private void cleanParams(HttpServletRequest request) {
        request.getSession().removeAttribute("error");
        request.getSession().removeAttribute("company");
        request.getSession().removeAttribute("id");
        request.getSession().removeAttribute("companyId");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "edit":
                editCompany(request, response);
                break;
            default:
                System.out.println("ACCION NO VALIDA: " + action);

                request.getSession().setAttribute("error", "Acción no válida");
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

                if (request.getSession().getAttribute("company") != null) {
                    updateCompany(request, response);
                } else {
                    addCompany(request, response);
                }

                break;
            case "delete":
                deleteCompany(request, response);
                break;
            default:
                System.out.println("ACCION NO VALIDA: " + action);
                request.getSession().setAttribute("error", "Acción no válida");
                redirect(request, response, LIST_COMPANIES_JSP);
                break;
        }
    }


    private void listCompanies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("LISTANDO COMPANIAS");

            cleanParams(request);
            String authToken = (String) request.getSession().getAttribute("token");
            System.out.println("TOKEN: " + authToken);

            Request request1 = new Request();
            request1.setAuthToken(authToken);
            Response loginSuccessful = port.getCOMPANIES(request1);

            if (loginSuccessful.isSUCCESS()) {
                List<Company> companies = loginSuccessful.getCOMPANIES();
                System.out.println(companies.toString());
                for (Company company : companies) {
                    System.out.println(company.toString());
                }

                request.getSession().setAttribute("companies", companies);

            } else {
                request.getSession().setAttribute("error", loginSuccessful.getMESSAGE());
                System.out.println("ERROR: " + loginSuccessful.getMESSAGE());
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            request.getSession().setAttribute("error", "Error al obtener las compañías: " + e.getMessage());
        } finally {
            redirect(request, response, LIST_COMPANIES_JSP);

        }
    }


    private void addCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println("AGREGANDO COMPANIA");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            String actividadEconomica = request.getParameter("actividadEconomica");
            String idIdentification = request.getParameter("idIdentification");


            CompanyDto companyDto = new CompanyDto();
            companyDto.setName(nombre);
            companyDto.setAddress(direccion);
            companyDto.setPhone(telefono);
            companyDto.setEmail(email);
            companyDto.setIdIdentification(idIdentification);
            companyDto.setEconomicActivity(actividadEconomica);


            String authToken = (String) request.getSession().getAttribute("token");
            System.out.println("TOKEN: " + authToken);


            companyDto.setAuthToken(authToken);

            Response createUserResponse = port.createCOMPANY(companyDto);

            if (createUserResponse.isSUCCESS()) {
                listCompanies(request, response);
            } else {
                request.getSession().setAttribute("error", createUserResponse.getMESSAGE());
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: Error al crear la compañía: " + e.getMessage());
            request.getSession().setAttribute("error", "Error al crear la compañía: " + e.getMessage());
        } finally {
            redirect(request, response, LIST_COMPANIES_JSP);

        }
    }

    private void editCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EDITANDO COMPANIA");
        String id = request.getParameter("id");

        if (id != null) {
            try {
                String authToken = (String) request.getSession().getAttribute("token");
                Request request1 = new Request();
                request1.setAuthToken(authToken);
                Response loginSuccessful = port.getCOMPANIES(request1);

                if (loginSuccessful.isSUCCESS()) {
                    List<Company> companies = loginSuccessful.getCOMPANIES();

                    for (Company company : companies) {
                        if (company.getID() == Integer.parseInt(id)) {
                            request.getSession().setAttribute("company", company);
                            System.out.println("COMPANY: " + company);
                            redirect(request, response, ADD_COMPANIES_JSP);
                            break;
                        }
                    }

                } else {
                    System.out.println("ERROR: " + loginSuccessful.getMESSAGE());
                    request.getSession().setAttribute("error", "Compañía no encontrada");
                    redirect(request, response, LIST_COMPANIES_JSP);
                }
            } catch (Exception e) {
                System.out.println("EXCEPTION: Error al editar la compañía: " + e.getMessage());
                request.getSession().setAttribute("error", "Error al editar la compañía: " + e.getMessage());
                redirect(request, response, LIST_COMPANIES_JSP);
            }
        } else {
            System.out.println("EXCEPTION: ID de compañía no proporcionado");
            request.getSession().setAttribute("error", "ID de compañía no proporcionado");
            redirect(request, response, LIST_COMPANIES_JSP);
        }
    }


    private void updateCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("EDITANDO COMPANIA");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String email = request.getParameter("email");
            String actividadEconomica = request.getParameter("actividadEconomica");
            String idIdentification = request.getParameter("idIdentification");


            CompanyDto companyDto = new CompanyDto();
            companyDto.setName(nombre);
            companyDto.setAddress(direccion);
            companyDto.setPhone(telefono);
            companyDto.setEmail(email);
            companyDto.setEconomicActivity(actividadEconomica);
            companyDto.setIdIdentification(idIdentification);

            String authToken = (String) request.getSession().getAttribute("token");
            System.out.println("TOKEN: " + authToken);

            companyDto.setAuthToken(authToken);

            System.out.println("DTO COMPANIA: " + companyDto.toString());
            System.out.println("ID A EDITAR: " + request.getParameter("companyId"));
            Response createUserResponse = port.updateCOMPANY(companyDto, Integer.parseInt(request.getParameter("companyId")));

            if (createUserResponse.isSUCCESS()) {
                listCompanies(request, response);
            } else {
                request.getSession().setAttribute("error", createUserResponse.getMESSAGE());
                redirect(request, response, LIST_COMPANIES_JSP);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: Error al actualizar la compañía: " + e.getMessage());
            request.getSession().setAttribute("error", "Error al actualizar la compañía: " + e.getMessage());
        } finally {
            redirect(request, response, LIST_COMPANIES_JSP);
        }
    }


    private void deleteCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            System.out.println("ID: " + id);

            if (id != null) {
                String authToken = (String) request.getSession().getAttribute("token");
                Request request1 = new Request();
                request1.setAuthToken(authToken);
                Response loginSuccessful = port.deleteCOMPANY(Integer.parseInt(id), request1);

                if (!loginSuccessful.isSUCCESS()) {

                    System.out.println("ERROR: " + loginSuccessful.getMESSAGE());
                    request.getSession().setAttribute("error", "Compañía no encontrada");
                    redirect(request, response, LIST_COMPANIES_JSP);

                }
            } else {
                System.out.println("EXCEPTION: ID de compañía no proporcionado");
                request.getSession().setAttribute("error", "ID de compañía no proporcionado");
                redirect(request, response, LIST_COMPANIES_JSP);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EXCEPTION: Error al borrar la compañía: " + e.getMessage());
            request.getSession().setAttribute("error", "Error al actualizar la compañía: " + e.getMessage());
        } finally {
            redirect(request, response, LIST_COMPANIES_JSP);
        }
    }


}
