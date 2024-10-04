package com.jaxws.services;

import com.jaxws.db.DbConnection;
import com.jaxws.dtos.Response;
import com.jaxws.models.Company;
import com.jaxws.models.User;
import com.jaxws.utils.HashUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyService {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    PreparedStatement preparedStatement;

    HashUtil hashUtil = new HashUtil();


    public CompanyService() {
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


    public Response getCompanies() throws SQLException {
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
                company.setIdIdentification(resultSet.getString("idIdentification"));
                company.setEconomicActivity(resultSet.getString("economicActivity"));
                company.setCreatedBy(resultSet.getInt("createdBy"));
                company.setUpdatedBy(resultSet.getInt("updatedBy"));
                company.setCreatedAt(resultSet.getDate("createdAt"));
                company.setUpdatedAt(resultSet.getDate("updatedAt"));

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
        response.setData(companies);
        return response;
    }


}
