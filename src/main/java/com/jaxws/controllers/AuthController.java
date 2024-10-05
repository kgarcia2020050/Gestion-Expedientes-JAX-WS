package com.jaxws.controllers;

import com.jaxws.dtos.Response;
import com.jaxws.dtos.UserDto;
import com.jaxws.services.AuthService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlRootElement;

@WebService(name = "AuthController", serviceName = "AuthController")
@XmlRootElement(name = "AUTH_RESPONSE")
public class AuthController {


    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }


    @WebMethod(operationName = "LOGIN", action = "LOGIN")
    public Response login(@WebParam(name = "EMAIL") String email, @WebParam(name = "PASSWORD") String password) {
        return authService.loginUser(email, password);
    }


    @WebMethod(operationName = "REGISTER", action = "REGISTER")
    public Response register(UserDto userDto) { return authService.registerUser(userDto); }


}
