package com.jaxws.controllers;

import com.jaxws.dtos.Response;
import com.jaxws.dtos.UserDto;
import com.jaxws.models.User;
import com.jaxws.services.UserService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.SQLException;
import java.util.List;

@WebService(name = "UserController", serviceName = "UserController")
@XmlRootElement(name = "USER_RESPONSE")
public class UserController {

    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }


    @WebMethod(operationName = "GET_USERS", action = "GET_USERS")
    @XmlElement(name = "USER")
    public Response getAllUsers() throws SQLException {
        return userService.getUsers();
    }


    @WebMethod(operationName = "UPDATE_USER", action = "UPDATE_USER")
    public Response updateUser(UserDto user, @WebParam(name = "ID") int id) throws SQLException {
        return userService.updateUser(user, id);
    }


}
