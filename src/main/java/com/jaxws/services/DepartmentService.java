package com.jaxws.services;

import com.jaxws.db.DbConnection;
import com.jaxws.dtos.CompanyDto;
import com.jaxws.dtos.DepartmentDto;
import com.jaxws.dtos.Response;
import com.jaxws.models.Company;
import com.jaxws.models.Department;
import com.jaxws.utils.Handler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    PreparedStatement preparedStatement;

    private final Handler handler;

    public DepartmentService() {
        this.statement = null;
        this.resultSet = null;
        this.preparedStatement = null;
        this.handler = new Handler();
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


    public Response getDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        DbConnection dbConnection = new DbConnection();

        Response response = new Response();

        try {
            connection = dbConnection.connect();
            statement = connection.createStatement();
            String SELECT_ALL_DEPARTMENTS = "SELECT * FROM [department]";
            resultSet = statement.executeQuery(SELECT_ALL_DEPARTMENTS);

            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setCompanyId(resultSet.getInt("companyId"));
                department.setDescription(resultSet.getString("description"));
                department.setCreatedBy(resultSet.getInt("createdBy"));
                department.setUpdatedBy(resultSet.getInt("updatedBy"));
                department.setCreatedAt(resultSet.getDate("createdAt"));
                department.setUpdatedBy(resultSet.getInt("updatedBy"));

                departments.add(department);
            }
            response.setMessage("Departamentos obtenidos correctamente.");
            response.setStatus(200);
            response.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error al seleccionar a los departamentos: " + e.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            dbConnection.disconnect();
            disconnect();
        }
        response.setData(departments);
        return response;
    }


    public Response createDepartment(DepartmentDto departmentDto) throws SQLException {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        Department department = new Department(departmentDto.getCompanyId(), departmentDto.getName(), departmentDto.getDescription());
        try {

            connection = dbConnection.connect();

            String sql = "INSERT INTO [department] (name, company_id, description, created_by, created_at) VALUES (?, ?, ?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getCompanyId());
            preparedStatement.setString(3, department.getDescription());
            preparedStatement.setInt(4, handler.getUserId());
            preparedStatement.setDate(5, department.getCreatedAt());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                response.setMessage("Departamento insertado correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al insertar el departamento: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            disconnect();
        }

        return response;
    }


    public Response updateDepartment(DepartmentDto departmentDto, int id) throws SQLException {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        Department department = new Department(departmentDto.getCompanyId(), departmentDto.getName(), departmentDto.getDescription());
        try {

            connection = dbConnection.connect();

            String sql = "UPDATE [department] SET name = ?, company_id = ?, description = ?, updated_by = ?, updated_at = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getCompanyId());
            preparedStatement.setString(3, department.getDescription());
            preparedStatement.setInt(4, handler.getUserId());
            preparedStatement.setDate(5, department.getUpdatedAt());
            preparedStatement.setInt(6, id);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                response.setMessage("Departamento actualizado correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al actualizar el departamento: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            disconnect();
        }

        return response;
    }

    public Response deleteDepartment(int id) throws SQLException {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        try {

            connection = dbConnection.connect();

            String sql = "DELETE FROM [department] WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                response.setMessage("Departamento eliminado correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al eliminar el departamento: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            disconnect();
        }

        return response;
    }

}
