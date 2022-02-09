package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.ReadFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVController {

    private ReadFile readFile;

    public void getFile(String fileName) {

        readFile = new ReadFile();
        readFile.readFile(fileName);

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
