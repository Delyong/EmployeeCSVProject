package com.sparta.employeecsv.model;
import java.sql.*;
import static com.sparta.employeecsv.model.ReadFile.logger;

public class EmployeeDatabase {
    //assuming conn = connection to database
    public static void main(String[] args) {
    try{
        String dropTable = "DROP TABLE IF EXISTS `EmployeeRecordsLarge`;"; //drop table if exists
        Statement st = conn.createStatement(); //prepare java statement
        ResultSet rs = st.executeQuery(dropTable); //execute the query
        st.close(); //close connection to database
    } catch (Exception e){
        logger.error("Error while dropping table", e);
    }

    try{
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
        Statement st = conn.createStatement(); //prepare java statement
        ResultSet rs = st.executeQuery(createTable); //execute the query
        st.close(); //close connection to database
    } catch (Exception e){
        logger.error("Error while creating the table", e);
    }
        


    }
}
