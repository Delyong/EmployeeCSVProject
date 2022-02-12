package com.sparta.employeecsvtest;

import com.sparta.employeecsv.controller.CSVController;
import com.sparta.employeecsv.model.Employee;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"1","55","100"})
    @DisplayName("Given numbers between 1-100, '1, 50, 100', checkThreadCount, returns true")
    void givenAValidNumber_checkThreadCount_returnsTrue(String count) {
        Assertions.assertTrue(controller.checkThreadCount(count));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0","-1","-100"})
    @DisplayName("Given numbers less than 1, '0, -1, -100', checkThreadCount, returns false")
    void givenANumberLessThanOne_checkThreadCount_returnsFalse(String count) {
        Assertions.assertFalse(controller.checkThreadCount(count));
    }

    @ParameterizedTest
    @ValueSource(strings = {"101","102","200"})
    @DisplayName("Given numbers over 100, '101, 102, 200', checkThreadCount, returns false")
    void givenANumberOverOneHundred_checkThreadCount_returnsFalse(String count) {
        Assertions.assertFalse(controller.checkThreadCount(count));
    }

    @Test
    @DisplayName("Given a non-number 'Hello', checkThreadCount, returns false")
    void givenANonNumber_checkThreadCount_returnsFalse() {
        Assertions.assertFalse(controller.checkThreadCount("Hello"));
    }
}
