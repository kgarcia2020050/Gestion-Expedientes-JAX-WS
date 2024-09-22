package com.jaxws.controllers;

import com.jaxws.dtos.Response;
import com.jaxws.dtos.UserDto;
import com.jaxws.services.UserService;
import jakarta.jws.HandlerChain;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.sql.SQLException;

@WebService(name = "UserController", serviceName = "UserController")
@XmlRootElement(name = "USER_RESPONSE")
@HandlerChain(file = "handler-chain.xml")
public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }


    @WebMethod(operationName = "GET_USERS", action = "GET_USERS")
    @XmlElement(name = "USER")
    public Response getAllUsers() throws SQLException {
        return userService.getUsers();
    }


    @WebMethod(operationName = "CREATE_USER", action = "CREATE_USER")
    public Response register(UserDto user) {
        try {

            return userService.createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @WebMethod(operationName = "UPDATE_USER", action = "UPDATE_USER")
    public Response updateUser(UserDto user, @WebParam(name = "ID") int id) throws SQLException {
        return userService.updateUser(user, id);
    }


}
