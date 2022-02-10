package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeDatabase;
import com.sparta.employeecsv.model.ReadFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import static com.sparta.employeecsv.CSVMain.logger;

public class CSVController implements Runnable {

    private ReadFile readFile;
    private EmployeeDatabase employeeDatabase;
    private Connection connection;

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

        try {
            connection = ConnectionFactory.getConnection();
            logger.info("Successfully created database connection");
        } catch (SQLException | IOException e) {
            logger.fatal("Failed to create database connection");
            e.printStackTrace();
        }

        employeeDatabase.dropTable(connection);
        employeeDatabase.createTable(connection);

    }

    public void insertRecordsToDatabase() {

        HashMap<String, Employee> employees = readFile.getEmployees();

        long startTime = System.nanoTime();
        employeeDatabase.insertRecords(connection, employees);
        logger.info("Writing to database took: " + (System.nanoTime() - startTime) + " nano seconds");
    }

    public void insertRecordsToDatabaseThreads() {


        Thread th1 = new Thread();
        Thread th2 = new Thread();

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        HashMap<String, Employee> employees = readFile.getEmployees();



        long startTime = System.nanoTime();
        employeeDatabase.insertRecords(connection, employees);
        logger.info("Writing to database took: " + (System.nanoTime() - startTime) + " nano seconds");
    }

    public void cleanUpDatabase() {
        try {
            ConnectionFactory.closeConnection();
            logger.info("Database has been closed");
        } catch (SQLException e) {
            logger.error("Error while closing the connection", e);
            e.printStackTrace();
        }
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
