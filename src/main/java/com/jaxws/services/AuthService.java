package com.jaxws.services;

import com.jaxws.db.DbConnection;
import com.jaxws.dtos.Response;
import com.jaxws.dtos.UserDto;
import com.jaxws.models.User;
import com.jaxws.utils.HashUtil;
import com.jaxws.utils.JwtUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class AuthService {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    PreparedStatement preparedStatement;

    HashUtil hashUtil = new HashUtil();


    public AuthService() {
        this.statement = null;
        this.resultSet = null;
        this.preparedStatement = null;
    }

    public void disconnect() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public Response loginUser(String email, String password)  {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        try {
            connection = dbConnection.connect();
            String sql = "SELECT id, password FROM [user] WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();



            if (resultSet.next()) {

                String hashedPassword = resultSet.getString("password");

                if (hashUtil.verifyPassword(password, hashedPassword)) {
                    String token = JwtUtil.generateToken(email, resultSet.getString("id"));
                    response.setToken(token);
                } else {
                    response.setMessage("Credenciales inválidas.");
                    response.setStatus(401);
                    response.setSuccess(false);
                    return response;
                }

                response.setMessage("Login exitoso.");
                response.setStatus(200);
                response.setSuccess(true);
            } else {
                response.setMessage("Credenciales inválidas.");
                response.setStatus(401);
                response.setSuccess(false);
            }
        } catch (SQLException e) {
            System.out.println(e);
            response.setMessage("Error al iniciar sesión: " + e.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            dbConnection.disconnect();
            disconnect();
        }
        return response;
    }


}
