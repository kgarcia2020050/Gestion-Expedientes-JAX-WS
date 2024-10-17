package com.frontend.frontend.controllers;


import com.frontend.frontend.wsdl.AuthController;
import com.frontend.frontend.wsdl.AuthController_Service;
import com.frontend.frontend.wsdl.Company;
import com.frontend.frontend.wsdl.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            AuthController_Service service = new AuthController_Service();
            AuthController port = service.getAuthControllerPort();
            Response loginSuccessful = port.login(email, password);

            if (loginSuccessful.isSUCCESS()) {


                // Cargar el archivo .jasper compilado
                InputStream reportStream = getServletContext()
                        .getResourceAsStream("/WEB-INF/reports/Cherry_1.jasper");
                if(reportStream == null) {
                    throw new Exception("No se pudo cargar el archivo del reporte");
                }

                // Crear datos de ejemplo
                List<Company> companies = new ArrayList<>();
                Company company = new Company();
                company.setID(1);
                company.setNAME("Company A");
                company.setADDRESS("123 Maple St");
                company.setPHONE("123-456-7890");
                company.setEMAIL("a@example.com");
                companies.add(company);

                // Configurar la fuente de datos del reporte
                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(companies);

                // Parámetros del reporte (vacío en este caso)
                Map<String, Object> parameters = new HashMap<>();
                JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parameters, dataSource);

                // Configurar la respuesta HTTP
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=companies_report.pdf");

                // Exportar el reporte a PDF
                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
                response.getOutputStream().flush();

//                String role = loginSuccessful.getROLE();
//                String token = loginSuccessful.getTOKEN();
//                request.getSession().setAttribute("role", role);
//                request.getSession().setAttribute("token", token);
//
//                if ("ADMIN".equals(role)) {
//                    response.sendRedirect(request.getContextPath() + "/components/private/admin/home/home.jsp");
//                } else if ("EMPLOYEE".equals(role)) {
//                    response.sendRedirect(request.getContextPath() + "/components/private/employee/home/home.jsp");
//                } else {
//                    request.getSession().setAttribute("error", "Credenciales inválidas. Intente nuevamente.");
//                    request.getRequestDispatcher(request.getContextPath() + "/components/public/auth/login.jsp").forward(request, response);
//
//                }
            } else {
//                request.getSession().setAttribute("error", "Credenciales inválidas. Intente nuevamente.");
//                request.getRequestDispatcher(request.getContextPath() + "/components/public/auth/login.jsp").forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();

//            request.getSession().setAttribute("error", "Error al registrarse: " + e.getMessage());
//            request.getRequestDispatcher(request.getContextPath() + "/components/public/auth/login.jsp").forward(request, response);

        }

    }
}
