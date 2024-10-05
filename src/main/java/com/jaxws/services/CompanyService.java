package com.jaxws.services;

import com.jaxws.db.DbConnection;
import com.jaxws.dtos.CompanyDto;
import com.jaxws.dtos.Response;
import com.jaxws.models.Company;
import com.jaxws.utils.Handler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyService {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    PreparedStatement preparedStatement;

    private final Handler handler;

    public CompanyService() {
        this.statement = null;
        this.resultSet = null;
        this.preparedStatement = null;
        this.handler = new Handler();
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

    public Response getCompanies()  {
        List<Company> companies = new ArrayList<>();
        DbConnection dbConnection = new DbConnection();

        Response response = new Response();

        try {
            connection = dbConnection.connect();
            statement = connection.createStatement();
            String SELECT_ALL_COMPANIES = "SELECT * FROM [company]";
            resultSet = statement.executeQuery(SELECT_ALL_COMPANIES);

            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));
                company.setAddress(resultSet.getString("address"));
                company.setPhone(resultSet.getString("phone"));
                company.setEmail(resultSet.getString("email"));
                company.setIdIdentification(resultSet.getString("id_identification"));
                company.setEconomicActivity(resultSet.getString("economic_activity"));
                company.setCreatedBy(resultSet.getInt("created_by"));
                company.setUpdatedBy(resultSet.getInt("updated_by"));
                company.setCreatedAt(resultSet.getDate("created_at"));
                company.setUpdatedAt(resultSet.getDate("updated_at"));

                companies.add(company);
            }
            response.setMessage("Compañias obtenidas correctamente.");
            response.setStatus(200);
            response.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error al seleccionar a las compañias: " + e.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            dbConnection.disconnect();
            disconnect();
        }
        response.setCompanies(companies);
        return response;
    }


    public Response createCompany(CompanyDto companyDto)  {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        Company company = new Company(companyDto.getName(), companyDto.getAddress(), companyDto.getPhone(),
                companyDto.getEmail(), companyDto.getIdIdentification(), companyDto.getEconomicActivity());
        try {

            connection = dbConnection.connect();

            String sql = "INSERT INTO [company] (name, address, phone, email, id_identification, economic_activity, created_by, created_at) VALUES (?, ?, ?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getAddress());
            preparedStatement.setString(3, company.getPhone());
            preparedStatement.setString(4, company.getEmail());
            preparedStatement.setString(5, company.getIdIdentification());
            preparedStatement.setString(6, company.getEconomicActivity());
            preparedStatement.setInt(7, handler.getUserId());
            preparedStatement.setDate(8, company.getCreatedAt());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                response.setMessage("Compañia insertada correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al insertar la compañia: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            disconnect();
        }

        return response;
    }


    public Response updateCompany(CompanyDto companyDto, int id)  {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        Company company = new Company(companyDto.getName(), companyDto.getAddress(), companyDto.getPhone(),
                companyDto.getEmail(), companyDto.getIdIdentification(), companyDto.getEconomicActivity());
        try {

            connection = dbConnection.connect();

            String sql = "UPDATE [company] SET name = ?, address = ?, phone = ?, " +
                    "email = ?, id_identification = ?, economic_activity = ?, updated_by = ?, updated_at = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getAddress());
            preparedStatement.setString(3, company.getPhone());
            preparedStatement.setString(4, company.getEmail());
            preparedStatement.setString(5, company.getIdIdentification());
            preparedStatement.setString(6, company.getEconomicActivity());
            preparedStatement.setInt(7, handler.getUserId());
            preparedStatement.setDate(8, company.getUpdatedAt());
            preparedStatement.setInt(9, id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                response.setMessage("Compañia actualizada correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al actualizar la compañia: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            disconnect();
        }

        return response;
    }


    public Response deleteCompany(int id)  {
        Response response = new Response();
        DbConnection dbConnection = new DbConnection();
        try {

            connection = dbConnection.connect();

            String sql = "DELETE FROM [company] WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                response.setMessage("Compañia eliminada correctamente.");
                response.setStatus(200);
                response.setSuccess(true);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setMessage("Error al eliminar la compañia: " + ex.getMessage());
            response.setStatus(500);
            response.setSuccess(false);
        } finally {
            disconnect();
        }

        return response;
    }

}
