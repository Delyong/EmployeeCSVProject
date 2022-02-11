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
import java.util.Arrays;
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

        employeeDatabase.dropTable();
        employeeDatabase.createTable();

    }

    public void insertRecordsToDatabase() {

        ArrayList<Employee> employees = readFile.getEmployeeAsList();

        long startTime = System.nanoTime();
        employeeDatabase.insertRecordsList(employees);

        System.out.println("Writing to database took: " + ((double)(System.nanoTime() - startTime)) / 1_000_000_000 + " seconds");
        logger.info("Writing to database took: " +  ((double)(System.nanoTime() - startTime)) / 1_000_000_000 + " seconds");
    }

    public void insertRecordsToDatabaseThreads() {

        long startTime = System.nanoTime();

        ArrayList<Employee> employees = readFile.getEmployeeAsList();

        Thread[] threads = createNumberOfThreads(4, employees);

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Writing to database took: " + ((double)(System.nanoTime() - startTime)) / 1_000_000_000 + " seconds");
        logger.info("Writing to database took: " +  ((double)(System.nanoTime() - startTime)) / 1_000_000_000 + " seconds");

    }

    private Thread[] createNumberOfThreads(int count, ArrayList<Employee> employees) {

        Thread[] threads = new Thread[count];
        int[] intervals = new int[count * 2];

        int employeesSize = employees.size();
        System.out.println(employeesSize);

        // 0 , same1, same1, same2, same2, same3, same3, employeesSize

        for (int i = 0; i < count; i++) {

            intervals[i * 2] = (employeesSize/count) * i;

            if ((2 * i) + 1 < intervals.length) {
                intervals[(i * 2) + 1] = (employeesSize/count) * (i+1);
            }

        }

        // make sure no values are lost to rounding
        intervals[intervals.length - 1] = employeesSize;

        System.out.println(Arrays.toString(intervals));
        System.out.println(employeesSize);

        for (int i = 0; i < count; i++) {

            List<Employee> splitEmployees = employees.subList(intervals[i*2], intervals[(i*2)+1]);

            InsertEmployeeThread insertEmployeeThread = new InsertEmployeeThread(
                    new ArrayList(splitEmployees)
            );

            threads[i] = new Thread(insertEmployeeThread);

        }

        return threads;
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
