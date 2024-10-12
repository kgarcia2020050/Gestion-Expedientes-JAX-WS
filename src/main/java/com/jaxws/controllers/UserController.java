package com.jaxws.controllers;

import com.jaxws.dtos.Request;
import com.jaxws.dtos.Response;
import com.jaxws.dtos.UserDto;
import com.jaxws.services.UserService;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@WebService(name = "UserController", serviceName = "UserController")
@XmlRootElement(name = "USER_RESPONSE")
public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    private static final String ERROR_MESSAGE = "Missing or invalid Authorization header";


    @WebMethod(operationName = "GET_USERS", action = "GET_USERS")
    @XmlElement(name = "USER")
    public Response getAllUsers(Request request) {

        if (!request.getAuthToken().isEmpty() && request.validateToken()) {
            return userService.getUsers();

        } else {
            throw new SecurityException(ERROR_MESSAGE);

        }


    }


    @WebMethod(operationName = "CREATE_USER", action = "CREATE_USER")
    public Response register(UserDto user) {

        if (!user.getAuthToken().isEmpty() && user.validateToken()) {
            return userService.createUser(user);
        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }

    }


    @WebMethod(operationName = "UPDATE_USER", action = "UPDATE_USER")
    public Response updateUser(UserDto user, @WebParam(name = "ID") int id) {

        if (!user.getAuthToken().isEmpty() && user.validateToken()) {
            return userService.updateUser(user, id);
        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }


    }


    @WebMethod(operationName = "DELETE_USER", action = "DELETE_USER")
    public Response deleteUser(@WebParam(name = "ID") int id, Request request) {


        if (!request.getAuthToken().isEmpty() && request.validateToken()) {
            return userService.deleteUser(id);
        } else {
            throw new SecurityException(ERROR_MESSAGE);
        }


    }


}
