package com.sparta.employeecsvtest;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.view.ReadFile;
import com.sun.jdi.IntegerValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {
    public static Logger logger = LogManager.getLogger("Project Logger");
    @Test
    @DisplayName("Check if getDuplicates works")
    void getDuplicates() {
        Employee emp1 = new Employee("1", "Mr", "John", "T", "Spiderman", "M", "test@test.com", "05/05/2000", "02/06/2019", "20000");
        Employee emp2 = new Employee("2", "Mr", "John", "T", "Spiderman", "M", "test@test.com", "05/05/2000", "02/06/2019", "20000");
        Employee emp3 = new Employee("1", "Mr", "John", "T", "Spiderman", "M", "test@test.com", "05/05/2000", "02/06/2019", "20000");


        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);

        final List<Employee> duplicates = ReadFile.getDuplicates(employeeList);

        logger.info("Duplicates: {}",duplicates);

    }
}