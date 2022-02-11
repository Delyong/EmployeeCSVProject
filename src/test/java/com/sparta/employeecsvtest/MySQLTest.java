package com.sparta.employeecsvtest;
import com.mysql.cj.jdbc.DatabaseMetaData;
import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
public class MySQLTest {
    private static EmployeeDatabase employee;
    @BeforeAll

    public static void setUp(){
        employee = new EmployeeDatabase();
    }
    private Connection connection = ConnectionFactory.getConnection();
    String dropTable = "DROP TABLE IF EXISTS `EmployeeRecords`;"; //drop table if exists
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

    @Test
    @DisplayName("Test table creation")
    public void checkDropCreateTable() throws SQLException {
        boolean exists = false;

        Statement st = connection.createStatement(); //prepare java statement
        employee.dropTable(); //execute the query
        employee.createTable();

        DatabaseMetaData dbmd = (DatabaseMetaData) connection.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = dbmd.getTables(null, null, "%", types);

        while (rs.next()) {
            if(rs.getString("TABLE_NAME").equals("EmployeeRecords")) exists = true;
        }

        st.close(); //close connection to database
        Assertions.assertTrue(exists);
    }

    @Test
    @DisplayName("Given a set of records to insert into the database, return true if the records are retrieved after insertion")
    public void checkInsertDataIntoTheTable() throws SQLException, ParseException {
        boolean exists = false;
        ArrayList<Employee> testRecords = new ArrayList<>();
        Employee e = new Employee(1, "Mr", "Mihai", 'T', "Udrea", 'M', "udreamihai@gmail.com", null, null, 75000F, 0);
        testRecords.add(e);
        System.out.println(testRecords);
        Statement st = connection.createStatement(); //prepare java statement
        st.executeUpdate(dropTable); //execute the query
        st.executeUpdate(createTable);

        employee.insertRecordsList(testRecords);//insert records

        ResultSet rs = st.executeQuery("SELECT * FROM `EmployeeRecords`;");
        while (rs.next()){
            int id = rs.getInt("EmployeeID");
            String fName = rs.getString("FirstName");
            String lName = rs.getString("LastName");
            if (id == 1 && fName.equals("Mihai") && lName.equals("Udrea")) exists = true;
        }
        st.close(); //close connection to database
        Assertions.assertTrue(exists);
    }

    public MySQLTest() throws SQLException, IOException {
    }
}
