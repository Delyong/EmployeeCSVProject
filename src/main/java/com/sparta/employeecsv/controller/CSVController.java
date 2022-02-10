package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeDatabase;
import com.sparta.employeecsv.model.InsertEmployeeThread;
import com.sparta.employeecsv.model.ReadFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        employeeDatabase.insertRecordsMap(connection, employees);

        System.out.println("Writing to database took: " + (System.nanoTime() - startTime) + " nano seconds");
        logger.info("Writing to database took: " + (System.nanoTime() - startTime) + " nano seconds");
    }

    public void insertRecordsToDatabaseThreads() {

        long startTime = System.nanoTime();

        ArrayList<Employee> employees = readFile.getEmployeeAsList();

        List<Employee> thread1List = employees.subList(0,employees.size()/4);
        List<Employee> thread2List = employees.subList(employees.size()/4, employees.size()/2);
        List<Employee> thread3List = employees.subList(employees.size()/2 ,(employees.size()/4) * 3);
        List<Employee> thread4List = employees.subList((employees.size()/4) * 3, employees.size());

        System.out.println((thread1List.size() + thread2List.size() + thread3List.size() + thread4List.size()));
        System.out.println((employees.size()));

        InsertEmployeeThread insertThread1 = new InsertEmployeeThread(
                connection, new ArrayList<Employee>(thread1List)
        );

        InsertEmployeeThread insertThread2 = new InsertEmployeeThread(
                connection, new ArrayList<Employee>(thread2List)
        );

        InsertEmployeeThread insertThread3 = new InsertEmployeeThread(
                connection, new ArrayList<Employee>(thread3List)
        );

        InsertEmployeeThread insertThread4 = new InsertEmployeeThread(
                connection, new ArrayList<Employee>(thread4List)
        );

        Thread thread1 = new Thread(insertThread1);
        Thread thread2 = new Thread(insertThread2);
        Thread thread3 = new Thread(insertThread3);
        Thread thread4 = new Thread(insertThread4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Writing to database took: " + (System.nanoTime() - startTime) + " nano seconds");
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
