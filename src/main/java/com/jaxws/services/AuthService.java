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


    public Response loginUser(String email, String password) {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        try {
            connection = dbConnection.connect();
            String sql = "SELECT id, password, role FROM [user] WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {

                String hashedPassword = resultSet.getString("password");
                String role = resultSet.getString("role");

                if (hashUtil.verifyPassword(password, hashedPassword)) {
                    String token = JwtUtil.generateToken(email, resultSet.getString("id"));
                    response.setToken(token);
                    response.setRole(role);
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

    public Response registerUser(UserDto userDto) {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        User myUser = new User(userDto.getEmail(), userDto.getFirstName(), userDto.getPassword(), userDto.getLastName(), true, userDto.getRole());
        try {

            myUser.setPassword(hashUtil.hashPassword(myUser.getPassword()));
            connection = dbConnection.connect();

            String sql = "INSERT INTO [user] (email, first_name, password, last_name," +
                    "active) VALUES (?, ?, ?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, myUser.getEmail());
            preparedStatement.setString(2, myUser.getFirstName());
            preparedStatement.setString(3, myUser.getPassword());
            preparedStatement.setString(4, myUser.getLastName());
            preparedStatement.setBoolean(5, myUser.isActive());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                response.setMessage("Usuario registrado correctamente.");
                response.setStatus(200);
                response.setSuccess(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al registrar al usuario: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }

        return response;
    }


}
