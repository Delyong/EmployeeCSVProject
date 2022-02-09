package com.sparta.employeecsv.model;
import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.view.CSVMain;

import java.io.IOException;
import java.sql.*;

public class EmployeeDatabase {

    private Connection connection;

    public void connectToDatabase() {
        try {
            connection = ConnectionFactory.getConnection();
            CSVMain.logger.info("Successfully created database connection");
        } catch (SQLException | IOException e) {
            CSVMain.logger.fatal("Failed to create database connection");
            e.printStackTrace();
        }
    }

    public void dropTable() {

        try {
            String dropTable = "DROP TABLE IF EXISTS `EmployeeRecords`;"; //drop table if exists

            Statement st = connection.createStatement(); //prepare java statement
            st.execute(dropTable); //execute the query

            CSVMain.logger.info("Successfully dropped 'EmployeeRecords' if exists");

            st.close(); //close connection to database

        } catch (Exception e) {
            CSVMain.logger.fatal("Error while dropping table", e);
            e.printStackTrace();
        }

    }

    public void createTable() {
        try {

            String createTable = "CREATE TABLE `EmployeeRecords` (" +
                    "`EmployeeID` INT," +
                    "`NamePrefix` VARCHAR(5)," +
                    "`FirstName` VARCHAR(30)," +
                    "`MiddleInitial` VARCHAR(1)," +
                    "`LastName` VARCHAR(30)," +
                    "`Gender` VARCHAR(1)," +
                    "`Email` VARCHAR(50)," +
                    "`DateOfBirth` DATE," +
                    "`DateOfJoining` DATE," +
                    "`Salary` DECIMAL(12,2)," +
                    "PRIMARY KEY (`EmployeeID`)" +
                    ");";

            Statement st = connection.createStatement(); //prepare java statement

            st.execute(createTable); //execute the query
            CSVMain.logger.info("Successfully created 'EmployeeRecords' table");

            st.close(); //close connection to database

        } catch (Exception e) {
            CSVMain.logger.fatal("Error while creating the table", e);
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            ConnectionFactory.closeConnection();
        } catch (SQLException e) {
            CSVMain.logger.error("Error while closing the connection", e);
            e.printStackTrace();
        }
    }

}
