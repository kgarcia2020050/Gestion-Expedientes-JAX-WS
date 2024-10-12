package com.jaxws.services;

import com.jaxws.db.DbConnection;
import com.jaxws.dtos.Response;
import com.jaxws.dtos.UserDto;
import com.jaxws.models.User;
import com.jaxws.utils.HashUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    PreparedStatement preparedStatement;

    HashUtil hashUtil = new HashUtil();


    public UserService() {
        this.statement = null;
        this.resultSet = null;
        this.preparedStatement = null;
    }

    public void disconnect() {
        try {
            if (resultSet != null) {
                resultSet.close();

            }
            if (statement != null) {
                statement.close();

            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Response getUsers() {
        List<User> users = new ArrayList<>();
        DbConnection dbConnection = new DbConnection();

        Response response = new Response();

        try {
            connection = dbConnection.connect();
            statement = connection.createStatement();
            String SELECT_ALL_USERS = "SELECT * FROM [user]";
            resultSet = statement.executeQuery(SELECT_ALL_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setLastLogin(resultSet.getDate("last_login"));
                user.setCreatedBy(resultSet.getInt("created_by"));
                user.setUpdatedBy(resultSet.getInt("updated_by"));
                user.setCreatedAt(resultSet.getDate("created_at"));
                user.setUpdatedAt(resultSet.getDate("updated_at"));
                user.setActive(resultSet.getBoolean("active"));
                users.add(user);
            }
            response.setMessage("Usuarios obtenidos correctamente.");
            response.setStatus(200);
            response.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error al seleccionar a los usuarios: " + e.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            dbConnection.disconnect();
            disconnect();
        }
        response.setUsers(users);
        return response;
    }


    public Response createUser(UserDto user) {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        User myUser = new User(user.getEmail(), user.getFirstName(), user.getPassword(), user.getLastName(), user.getIdToken(), user.getIdToken(), true, user.getRole());
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

    public Response updateUser(UserDto user, int id) {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        User myUser = new User(user.getEmail(), user.getFirstName(), user.getPassword(), user.getLastName(), user.getIdToken(), true, user.getRole());
        myUser.setId(id);
        try {

            connection = dbConnection.connect();

            String sql = "UPDATE [user] SET email = ?, password = ?, first_name = ?, last_name = ?, " +
                    "updated_by = ?, updated_at = ?, active = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, myUser.getEmail());
            preparedStatement.setString(2, myUser.getPassword());
            preparedStatement.setString(3, myUser.getFirstName());
            preparedStatement.setString(4, myUser.getLastName());
            preparedStatement.setInt(5, myUser.getUpdatedBy());
            preparedStatement.setDate(6, myUser.getUpdatedAt());
            preparedStatement.setBoolean(7, myUser.isActive());
            preparedStatement.setInt(8, myUser.getId());


            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                response.setMessage("Usuario modificado correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al modificar al usuario: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            dbConnection.disconnect();
            disconnect();
        }

        return response;
    }


    public Response deleteUser(int id) {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        try {

            connection = dbConnection.connect();

            String sql = "UPDATE [user] SET active = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                response.setMessage("Usuario eliminado correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al eliminar al usuario: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            dbConnection.disconnect();
            disconnect();
        }

        return response;
    }

}
