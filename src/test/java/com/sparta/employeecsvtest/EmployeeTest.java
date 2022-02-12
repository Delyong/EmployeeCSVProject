package com.sparta.employeecsvtest;

import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class EmployeeTest {

    @DisplayName("Given a employee record with no null values, isRecordValid should return true")
    @Test
    public void givenAValidEmployee_isRecordValid_returnsTrue() {
        Employee e = new Employee(1, "Mr", "Mihai",
                'T', "Udrea", 'M',
                "udreamihai@gmail.com", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                75000F);

        Assertions.assertTrue(e.isRecordValid());
    }

    @DisplayName("Given a employee record with null employeeID, isRecordValid should return false")
    @Test
    public void givenAInValidEmployeeID_isRecordValid_returnsFalse() {
        Employee e = new Employee(null, "Mr", "Mihai",
                'T', "Udrea", 'M',
                "udreamihai@gmail.com", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                75000F);

        Assertions.assertFalse(e.isRecordValid());
    }

    @DisplayName("Given a employee record with null gender, isRecordValid should return false")
    @Test
    public void givenAInValidEmployeeGender_isRecordValid_returnsFalse() {
        Employee e = new Employee(1, "Mr", "Mihai",
                'T', "Udrea", null,
                "udreamihai@gmail.com", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                75000F);

        Assertions.assertFalse(e.isRecordValid());
    }

    @DisplayName("Given two employees with the same employee id, equals should return true")
    @Test
    public void givenTwoEmployeesSameEmployeeID_equals_returnsTrue() {

        Employee e1 = new Employee(1, "Mr", "Mihai",
                'T', "Udrea", 'M',
                "udreamihai@gmail.com", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                75000F);

        Employee e2 = new Employee(1, "Mr", "Roberto",
                'M', "Lovece", 'M',
                "rlovece@hotmail.co.uk", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                750000F);

        Assertions.assertTrue(e1.equals(e2));
    }


    @DisplayName("Given two employees with different employee id, equals should return false")
    @Test
    public void givenTwoEmployeesDifferentEmployeeID_equals_returnsFalse() {

        Employee e1 = new Employee(1, "Mr", "Mihai",
                'T', "Udrea", null,
                "udreamihai@gmail.com", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                75000F);

        Employee e2 = new Employee(2, "Mr", "Mihai",
                'T', "Udrea", null,
                "udreamihai@gmail.com", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                75000F);

        Assertions.assertFalse(e1.equals(e2));
    }


    @DisplayName("Given two employees, one null, equals should return false")
    @Test
    public void givenTwoEmployeesOneNull_equals_returnsFalse() {

        Employee e1 = new Employee(1, "Mr", "Mihai",
                'T', "Udrea", null,
                "udreamihai@gmail.com", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                75000F);

        Assertions.assertFalse(e1.equals(null));
    }



}
