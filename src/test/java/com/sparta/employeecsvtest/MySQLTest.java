package com.sparta.employeecsvtest;
import com.mysql.cj.jdbc.DatabaseMetaData;
import com.sparta.employeecsv.database.ConnectionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class MySQLTest {
    private Connection connection = ConnectionFactory.getConnection();
    @Test
    @DisplayName("Test table creation")
    public void checkDropTable() throws SQLException {
        boolean exists = false;
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

        Statement st = connection.createStatement(); //prepare java statement
        st.executeUpdate(dropTable); //execute the query
        st.executeUpdate(createTable);
        DatabaseMetaData dbmd = (DatabaseMetaData) connection.getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = dbmd.getTables(null, null, "%", types);
        while (rs.next()) {
            if(rs.getString("TABLE_NAME").equals("EmployeeRecords")) exists = true;
        }
        st.close(); //close connection to database
        Assertions.assertTrue(exists);
    }

    public MySQLTest() throws SQLException, IOException {
    }
}
