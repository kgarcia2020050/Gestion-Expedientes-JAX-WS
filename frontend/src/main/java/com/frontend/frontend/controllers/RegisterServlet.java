package com.frontend.frontend.controllers;

import com.frontend.frontend.wsdl.AuthController;
import com.frontend.frontend.wsdl.AuthController_Service;
import com.frontend.frontend.wsdl.Response;
import com.frontend.frontend.wsdl.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthController_Service service = new AuthController_Service();
        AuthController port = service.getAuthControllerPort();

        UserDto userRequest = new UserDto();
        userRequest.setEMAIL(email);
        userRequest.setFIRSTNAME(firstName);
        userRequest.setLASTNAME(lastName);
        userRequest.setPASSWORD(password);
        userRequest.setROLE("EMPLOYEE");

        Response createUserResponse = port.register(userRequest);

        if (createUserResponse.isSUCCESS()) {
            response.sendRedirect(request.getContextPath() + "/components/public/auth/login.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/error-page/notFound.jsp");
        }
    }
}
