package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeDatabase;
import com.sparta.employeecsv.model.ReadFile;

import java.util.HashMap;

import static com.sparta.employeecsv.view.CSVMain.logger;

public class CSVController implements Runnable {

    private ReadFile readFile;
    private EmployeeDatabase employeeDatabase;

    public void getFile(String fileName) {

        readFile = new ReadFile();
        readFile.readFile(fileName);

    }

    @Override
    public void run() {
        insertRecordsToDatabase();
    }

    public void setupDatabase() {

        logger.info("Database has been set up");
        employeeDatabase = new EmployeeDatabase();

        employeeDatabase.connectToDatabase();
        employeeDatabase.dropTable();
        employeeDatabase.createTable();

    }

    public void insertRecordsToDatabase() {

        HashMap<String, Employee> employees = readFile.getEmployees();

        long startTime = System.nanoTime();
        employeeDatabase.insertRecords(employees);
        logger.info("Writing to database took: " + (System.nanoTime() - startTime) + " nano seconds");
    }

    public void cleanUpDatabase() {
        logger.info("Database has been closed");
        employeeDatabase.closeConnection();
    }

    public int getUniqueCount() {
        logger.info("Amount of unique records has been displayed");
        return readFile.getEmployees().size();
    }

    public int getDuplicateCount() {
        logger.info("Amount of duplicated records has been displayed");
        return readFile.getDuplicates().size();
    }

    public String getDuplicatesString() {
        logger.info("Duplicate records has been displayed to the user");
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
        logger.info("Amount of corrupted records has been displayed");
        return corruptCount;

    }
}
