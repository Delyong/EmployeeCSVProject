package com.sparta.employeecsv.model;
import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.view.CSVMain;

import java.io.IOException;
import java.sql.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.sparta.employeecsv.view.CSVMain.logger;
import static com.sparta.employeecsv.database.ConnectionFactory.*;
import static com.sparta.employeecsv.model.ReadFile.*;

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

    public void insertRecords(HashMap<String, Employee> employees){
    //insert values into the table

        String sqlInsert =
                "INSERT INTO EmployeeRecords " +
                "(EmployeeID, NamePrefix, FirstName, MiddleInitial, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            Iterator empIterator = employees.entrySet().iterator();

            while (empIterator.hasNext()) {
                Map.Entry mapElement
                        = (Map.Entry)empIterator.next();
                preparedStatement.setInt(1, ((int)mapElement.getValue()));
                preparedStatement.setString(2,((String)mapElement.getValue()));
                preparedStatement.setString(3,((String)mapElement.getValue()));
                preparedStatement.setString(4,((String)mapElement.getValue()));
                preparedStatement.setString(5,((String)mapElement.getValue()));
                preparedStatement.setString(6,((String)mapElement.getValue()));
                preparedStatement.setString(7,((String)mapElement.getValue()));
                preparedStatement.setString(8,((String)mapElement.getValue()));
                preparedStatement.setString(9,((String)mapElement.getValue()));
                preparedStatement.setInt(10,((int)mapElement.getValue()));
            }

        } catch (Exception e){
            logger.error("Error while inserting data into the table", e);
        }
        }

    }

}