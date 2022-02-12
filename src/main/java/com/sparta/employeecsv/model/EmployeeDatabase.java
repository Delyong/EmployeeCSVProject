package com.sparta.employeecsv.model;
import com.sparta.employeecsv.CSVMain;
import com.sparta.employeecsv.database.ConnectionFactory;

import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.sparta.employeecsv.CSVMain.logger;

public class EmployeeDatabase {

    private Connection connection;

    public EmployeeDatabase() {
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException | IOException e) {
            logger.fatal("Failed to get database connection");
            e.printStackTrace();
        }
    }

    public void dropTable() {
    
        try {
            String dropTable = "DROP TABLE IF EXISTS `EmployeeRecords`;"; //drop table if exists
    
            Statement st = connection.createStatement(); //prepare java statement
            st.executeUpdate(dropTable); //execute the query
    
            logger.info("Successfully dropped 'EmployeeRecords' if exists");
    
            st.close(); //close connection to database
    
        } catch (SQLException e) {
            logger.error("Error while dropping table" + e); //add error into the log file
            e.printStackTrace();
        }
    
    }
    
    public void createTable() {
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
    
        } catch (SQLException e) {
            logger.fatal("Error while creating the table", e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertRecordsList(ArrayList<Employee> employees){
    //insert values into the table

        String sqlInsert =
                "INSERT INTO EmployeeRecords " +
                "(EmployeeID, NamePrefix, FirstName, MiddleInitial, LastName, Gender, Email, DateOfBirth, DateOfJoining, Salary) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            Iterator empIterator = employees.iterator();
            for (Employee employee : employees) {
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

            }

            connection.commit();
            connection.setAutoCommit(true);

            preparedStatement.close();

        } catch (Exception e){
            logger.error("Error while inserting data into the table", e); //add error into the log file
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {

        String query = "SELECT * FROM EmployeeRecords";
        List<Employee> list = new ArrayList<Employee>();
        Employee employee = null;
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                employee = new Employee();
                // Retrieve one employee details and store it in employee object
                employee.setEmployeeID(rs.getInt("EmployeeID"));
                employee.setNamePrefix(rs.getString("NamePrefix"));
                employee.setFirstName(rs.getString("FirstName"));
                String middleInitial = rs.getString("MiddleInitial");
                employee.setMiddleInitial(middleInitial.charAt(0));
                employee.setLastName(rs.getString("LastName"));
                String gender = rs.getString("Gender");
                employee.setGender(gender.charAt(0));
                employee.setEmail(rs.getString("Email"));
                employee.setDateOfBirth(rs.getDate("DateOfBirth"));
                employee.setDateOfJoin(rs.getDate("DateOfJoining"));
                employee.setSalary(rs.getFloat("Salary"));

                //add each employee to the list
                list.add(employee);
            }
        } catch (Exception e) {
            logger.fatal("Error while retrieving the employees from the table", e);
            e.printStackTrace();
        }
        return list;
    }

    public Employee getEmployeeByEmpId(int empId) {

        String query = "SELECT * FROM EmployeeRecords where EmployeeID = ?";
        List<Employee> list = new ArrayList<Employee>();
        Employee employee = null;
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, empId);
            rs = statement.executeQuery();
            while (rs.next()) {
                employee = new Employee();
                // Retrieve one employee details and store it in employee object
                employee.setEmployeeID(rs.getInt("EmployeeID"));
                employee.setNamePrefix(rs.getString("NamePrefix"));
                employee.setFirstName(rs.getString("FirstName"));
                String middleInitial = rs.getString("MiddleInitial");
                employee.setMiddleInitial(middleInitial.charAt(0));
                employee.setLastName(rs.getString("LastName"));
                String gender = rs.getString("Gender");
                employee.setGender(gender.charAt(0));
                employee.setEmail(rs.getString("Email"));
                employee.setDateOfBirth(rs.getDate("DateOfBirth"));
                employee.setDateOfJoin(rs.getDate("DateOfJoining"));
                employee.setSalary(rs.getFloat("Salary"));
            }
        } catch (Exception e) {
            logger.fatal("Error while retrieving the employees from the table", e);
            e.printStackTrace();
        }
        return employee;
    }
}