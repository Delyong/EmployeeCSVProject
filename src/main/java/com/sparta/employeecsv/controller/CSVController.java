package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeDatabase;
import com.sparta.employeecsv.model.ReadFile;

import java.util.HashMap;

public class CSVController {

    private ReadFile readFile;
    private EmployeeDatabase employeeDatabase;

    public void getFile(String fileName) {

        readFile = new ReadFile();
        readFile.readFile(fileName);

    }

    public void setupDatabase() {

        employeeDatabase = new EmployeeDatabase();

        employeeDatabase.connectToDatabase();
        employeeDatabase.dropTable();
        employeeDatabase.createTable();

    }

    public void cleanUpDatabase() {
        employeeDatabase.closeConnection();
    }

    public int getUniqueCount() {
        return readFile.getEmployees().size();
    }

    public int getDuplicateCount() {
        System.out.println(readFile.getDuplicates().toString());
        return readFile.getDuplicates().size();
    }

    public String getDuplicatesString() {
        return readFile.getDuplicates().toString();
    }

    public int getCorruptedCount() {

        int corruptCount = 0;
        HashMap<String, Employee> employees = readFile.getEmployees();

        for (String id : employees.keySet()) {
            if (!employees.get(id).isRecordValid()) {
                corruptCount++;
            }
        }

        return corruptCount;

    }

}
