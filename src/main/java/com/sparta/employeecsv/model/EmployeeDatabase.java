package com.sparta.employeecsv.model;
import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.view.CSVMain;

import java.sql.*;

public class EmployeeDatabase {
    //assuming conn = connection to database
    public static void main(String[] args) {
    try {
        Connection connection = ConnectionFactory.getConnection();
        String dropTable = "DROP TABLE IF EXISTS `EmployeeRecordsLarge`;"; //drop table if exists
        Statement st = connection.createStatement(); //prepare java statement
        ResultSet rs = st.executeQuery(dropTable); //execute the query
        st.close(); //close connection to database
    } catch (Exception e){
        CSVMain.logger.error("Error while dropping table", e);
    }

    try{

        Connection connection = ConnectionFactory.getConnection();

        String createTable = "CREATE TABLE `EmployeeRecordsLarge` (" +
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
        ResultSet rs = st.executeQuery(createTable); //execute the query
        st.close(); //close connection to database

    } catch (Exception e){
        CSVMain.logger.error("Error while creating the table", e);
    }
        


    }
}
