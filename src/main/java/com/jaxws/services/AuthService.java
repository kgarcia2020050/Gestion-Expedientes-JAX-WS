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


    public Response createUser(UserDto user) throws SQLException {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        User myUser = new User(user.getEmail(), user.getFirstName(), user.getPassword(), user.getLastName(), user.getCreatedBy(), user.getUpdatedBy(), user.isActive());
        try {

            myUser.setPassword(hashUtil.hashPassword(myUser.getPassword()));
            connection = dbConnection.connect();

            String sql = "INSERT INTO [user] (email, password, first_name, last_name, last_login, " +
                    "created_by,updated_by,created_at,updated_at,active) VALUES (?, ?, ?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, myUser.getEmail());
            preparedStatement.setString(2, myUser.getPassword());
            preparedStatement.setString(3, myUser.getFirstName());
            preparedStatement.setString(4, myUser.getLastName());
            preparedStatement.setDate(5, myUser.getLastLogin());
            preparedStatement.setInt(6, myUser.getCreatedBy());
            preparedStatement.setInt(7, myUser.getUpdatedBy());
            preparedStatement.setDate(8, myUser.getCreatedAt());
            preparedStatement.setDate(9, myUser.getUpdatedAt());
            preparedStatement.setBoolean(10, myUser.isActive());


            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                response.setMessage("Usuario insertado correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al insertar al usuario: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }

        return response;
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
