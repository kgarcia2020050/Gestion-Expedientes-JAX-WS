package com.jaxws.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public String user, password, url, driver;
    public Connection connection;

    public DbConnection() {
        this.user = "sa";
        this.password = "123456";
        this.url = "jdbc:sqlserver://localhost:1433;databaseName=case_management_system;encrypt=false;trustServerCertificate=true";
        this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        this.connection = null;
    }


    public Connection connect() {
        try {
            System.out.println("Connecting to the database");
            System.out.println(driver);
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void disconnect() {
        try {
            connection.close();
            System.out.println("Disconnected from the database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
