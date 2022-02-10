package com.sparta.employeecsv.model;
import com.sparta.employeecsv.CSVMain;
import java.sql.*;

import java.util.HashMap;
import java.util.Iterator;

import static com.sparta.employeecsv.CSVMain.logger;

public class EmployeeDatabase {

    private Connection connection;
    
    public void dropTable(Connection connection) {
    
        try {
            String dropTable = "DROP TABLE IF EXISTS `EmployeeRecords`;"; //drop table if exists
    
            Statement st = connection.createStatement(); //prepare java statement
            st.executeUpdate(dropTable); //execute the query
    
            logger.info("Successfully dropped 'EmployeeRecords' if exists");
    
            st.close(); //close connection to database
    
        } catch (Exception e) {
            logger.error("Error while dropping table", e); //add error into the log file
            e.printStackTrace();
        }
    
    }
    
    public void createTable(Connection connection) {
        try {
    
            String createTable = "CREATE TABLE `EmployeeRecords` (" +
                    "`EmployeeID` INT," +
                    "`NamePrefix` VARCHAR(5)," +
                    "`FirstName` VARCHAR(30)," +
                    "`MiddleInitial` CHAR(1)," +
                    "`LastName` VARCHAR(30)," +
                    "`Gender` CHAR(1)," +
                    "`Email` VARCHAR(50)," +
                    "`DateOfBirth` DATE," +
                    "`DateOfJoining` DATE," +
                    "`Salary` DECIMAL(12,2)," +
                    "PRIMARY KEY (`EmployeeID`)" +
                    ");";
    
            Statement st = connection.createStatement(); //prepare java statement
    
            st.executeUpdate(createTable); //execute the query
            CSVMain.logger.info("Successfully created 'EmployeeRecords' table");
    
            st.close(); //close connection to database
    
        } catch (Exception e) {
            logger.fatal("Error while creating the table", e);
            e.printStackTrace();
        }
    }
    
    public void insertRecords(Connection connection, HashMap<String, Employee> employees){
    //insert values into the table

        String sqlInsert =
                "INSERT INTO EmployeeRecords " +
                "(EmployeeID, NamePrefix, FirstName, MiddleInitial, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            Iterator empIterator = employees.entrySet().iterator();
                for (Employee employee : employees.values()) {
                    synchronized (employee) {
                        preparedStatement.setInt(1, employee.getEmployeeID());
                        preparedStatement.setString(2, employee.getNamePrefix());
                        preparedStatement.setString(3, employee.getFirstName());
                        preparedStatement.setString(4, employee.getMiddleInitial().toString());
                        preparedStatement.setString(5, employee.getLastName());
                        preparedStatement.setString(6, employee.getGender().toString());
                        preparedStatement.setString(7, employee.getEmail());
                        preparedStatement.setDate(8, employee.getDateOfBirth());
                        preparedStatement.setDate(9, employee.getDateOfJoin());
                        preparedStatement.setFloat(10, employee.getSalary());

                        preparedStatement.executeUpdate();

                        System.out.print("Added record: " + employee.toString());
                    }
                }

            preparedStatement.close();

        } catch (Exception e){
            logger.error("Error while inserting data into the table", e); //add error into the log file
            e.printStackTrace();
        }

    }

}