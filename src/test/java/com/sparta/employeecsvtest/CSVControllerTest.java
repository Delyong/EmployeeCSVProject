package com.sparta.employeecsvtest;

import com.sparta.employeecsv.controller.CSVController;
import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CSVControllerTest {

    private static CSVController controller;

    @BeforeAll
    static void setUp() {
        controller = new CSVController();
    }

    @Test
    @DisplayName("Given a thread count of 5, threads length equals 5")
    void givenAThreadCount_threadsLength_equalsThreadCount() {

        ArrayList<Employee> employees = new ArrayList<>(
                List.of(new Employee(), new Employee(), new Employee(),
                        new Employee(), new Employee())
        );

        Thread[] threads = controller.createNumberOfThreads(5, employees);
        Assertions.assertEquals(5, threads.length);
    }

}
