package com.frontend.frontend.controllers;


import com.frontend.frontend.wsdl.AuthController;
import com.frontend.frontend.wsdl.AuthController_Service;
import com.frontend.frontend.wsdl.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthController_Service service = new AuthController_Service();
        AuthController port = service.getAuthControllerPort();

        Response loginSuccessful = port.login(email, password);

        if (loginSuccessful.isSUCCESS()) {
            response.sendRedirect(request.getContextPath() + "/views/main/index.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/error-page/notFound.jsp");
        }

    }
}
