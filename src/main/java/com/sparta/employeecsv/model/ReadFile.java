package com.sparta.employeecsv.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadFile {

    private List<Employee> employees;
    private List<Employee> duplicates;

    public void readFileLambda(String fileName) {

        employees = new ArrayList<>();

        EmployeeParser employeeParser = new EmployeeParser();

        try { //read data from the file
            List<Employee> original = Files.lines(Path.of(fileName))
                    .map(s -> {
                        String[] data = s.split(",");
                        return employeeParser.parseEmployee(data[0], data[1], data[2],
                                data[3], data[4], data[5], data[6],
                                data[7], data[8], data[9]);
                    })
                    .toList();

            employees = original.stream().distinct().toList();
            duplicates = original.stream().distinct().filter(i -> Collections.frequency(original, i) > 1).toList();

            System.out.println(original.size());
            System.out.println(duplicates.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Employee> getEmployeeAsList() {
        ArrayList<Employee> employeesList = new ArrayList<Employee>(employees.size());

        for (Employee employee : employees) {
            employeesList.add(employee);
        }

        return employeesList;
    }

    public List<Employee> getDuplicates() {
        return duplicates;
    }

}
