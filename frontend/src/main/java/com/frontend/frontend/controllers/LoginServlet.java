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


                String role = loginSuccessful.getROLE();
                String token = loginSuccessful.getTOKEN();
                request.getSession().setAttribute("role", role);
                request.getSession().setAttribute("token", token);

                if ("ADMIN".equals(role)) {
                    response.sendRedirect(request.getContextPath() + "/components/private/admin/home/home.jsp");
                } else if ("EMPLOYEE".equals(role)) {
                    response.sendRedirect(request.getContextPath() + "/components/private/employee/home/home.jsp");
                } else {
                    request.getSession().setAttribute("error", "Credenciales inválidas. Intente nuevamente.");
                    request.getRequestDispatcher(request.getContextPath() + "/components/public/auth/login.jsp").forward(request, response);

                }
            } else {
                request.getSession().setAttribute("error", "Credenciales inválidas. Intente nuevamente.");
                request.getRequestDispatcher(request.getContextPath() + "/components/public/auth/login.jsp").forward(request, response);

            }
        } catch (Exception e) {
            e.printStackTrace();

            request.getSession().setAttribute("error", "Error al registrarse: " + e.getMessage());
            request.getRequestDispatcher(request.getContextPath() + "/components/public/auth/login.jsp").forward(request, response);

        }

    }
}
