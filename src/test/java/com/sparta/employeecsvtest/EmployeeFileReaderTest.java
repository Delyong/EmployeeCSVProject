package com.sparta.employeecsvtest;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

public class EmployeeFileReaderTest {

    private static EmployeeFileReader efr;

    @BeforeAll
    static void setUp() {
        efr = new EmployeeFileReader();
    }

    @DisplayName("Given a valid file path 'EmployeeRecordsTest.csv', the 2nd entry of the file, matches the expected")
    @Test
    public void givenAValidFile_anEntryOfTheFile_matchesTheExpected() {

        efr.readFileLambda("EmployeeRecordsTest.csv");
        ArrayList<Employee> employees = efr.getEmployeesList();

        Employee employee = employees.get(1);
        System.out.println(employee);

        // 2,Mr.,Titus,X,Shrewsbury,M,titus.shrewsbury@hotmail.com,06/10/1993,6/14/2017,190460
        boolean check = (employee.getEmployeeID() == 2) && (employee.getNamePrefix().equals("Mr.")) &&
                (employee.getFirstName().equals("Titus")) && (employee.getMiddleInitial() == 'X') &&
                (employee.getLastName().equals("Shrewsbury")) && (employee.getGender() == 'M') &&
                (employee.getEmail().equals("titus.shrewsbury@hotmail.com")) &&
                (employee.getDateOfBirth().equals(Date.valueOf("1993-06-10"))) &&
                (employee.getDateOfJoin().equals(Date.valueOf("2017-06-14"))) &&
                (employee.getSalary() == 190460.0);

        Assertions.assertTrue(check);

    }

    @DisplayName("Given a valid file path 'EmployeeRecordsDuplicateTest.csv', full of duplicates, duplicates size should be equal to lines")
    @Test
    public void givenAValidFileOfDuplicates_duplicatesSize_matchesFileLinesCount() {

        efr.readFileLambda("EmployeeRecordsDuplicateTest.csv");
        ArrayList<Employee> duplicates = efr.getDuplicates();

        Assertions.assertEquals(1, duplicates.size());

    }

    @DisplayName("Given a valid file path 'EmployeeRecordsTest.csv', employees size should be equal to lines of file")
    @Test
    public void givenAValidFile_employeeSize_matchesFileLinesCount() {

        efr.readFileLambda("EmployeeRecordsTest.csv");
        ArrayList<Employee> employees = efr.getEmployeesList();

        Assertions.assertEquals(3, employees.size());

    }

}
