package com.sparta.employeecsv.controller;

import com.sparta.employeecsv.database.ConnectionFactory;
import com.sparta.employeecsv.model.Employee;
import com.sparta.employeecsv.model.EmployeeDatabase;
import com.sparta.employeecsv.model.InsertEmployeeThread;
import com.sparta.employeecsv.model.EmployeeFileReader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sparta.employeecsv.CSVMain.logger;

/**
 * A controller class that acts as a bridge between the model classes and the view classes
 */
public class CSVController {

    private EmployeeFileReader employeeFileReader;
    private EmployeeDatabase employeeDatabase;

    /**
     * Creates an instance of the file reader and read the file
     *
     * @param fileName - The name of the file that is wished to read, as a string value
     */
    public void readFile(String fileName) {
        employeeFileReader = new EmployeeFileReader();
        employeeFileReader.readFileLambda(fileName);
    }

    /**
     * Returns the selected employee's ID
     *
     * @param employeeId - Employee's primary key, as an integer value
     * @return employeeId
     */
    public Employee getEmployeeById(int employeeId){
        return employeeDatabase.getEmployeeByEmpId(employeeId);
    }

    /**
     * Returns the list of employee records that were read from the CSV file
     *
     * @return employee list
     */
    public List<Employee> getEmployees(){
        return employeeDatabase.getEmployees();
    }

    /**
     * Conducts the following set up to ensure the database is ready for querying
     */
    public void setupDatabase() {
        employeeDatabase = new EmployeeDatabase();

        employeeDatabase.dropTable();
        employeeDatabase.createTable();
    }

    /**
     * Inserts the employee records to the database using multithreading
     *
     * @param threadCount - The amount of threads that the user wish to execute the program with
     * @return Writing timing
     */
    public long insertRecordsToDatabaseThreads(int threadCount) {

        ArrayList<Employee> employees = employeeFileReader.getEmployeesList();

        Thread[] threads = createNumberOfThreads(threadCount, employees);    // Creates the list of threads
        logger.debug("Created: " + threads.length + " threads, " + "Expected: " + threadCount + " threads");

        for (Thread thread : threads) {    // For each thread created in the list, start and join the threads
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.warn("Can't join thread " + thread.getName(), e.getMessage(), e);
                e.printStackTrace();
            }
        }
        return System.nanoTime();
    }

    /**
     * Creates the list of threads which is dependent on user count input
     *
     * @param count - Number threads to be created
     * @param employees - List of employee records
     * @return A list which consists of all the threads to be executed
     */
    public Thread[] createNumberOfThreads(int count, ArrayList<Employee> employees) {

        Thread[] threads = new Thread[count];
        int[] intervals = new int[count * 2];    // declares the intervals between threads

        int employeesSize = employees.size();

        // 0 , same1, same1, same2, same2, same3, same3, employeesSize
        for (int i = 0; i < count; i++) {

            // Sets each interval based off the employee size and how many threads to create
            intervals[i * 2] = (employeesSize/count) * i;

            if ((2 * i) + 1 < intervals.length) {
                intervals[(i * 2) + 1] = (employeesSize/count) * (i+1);
            }
        }

        // Make sure no values are lost to rounding
        intervals[intervals.length - 1] = employeesSize;
        logger.debug("Dividing employees with intervals: " + Arrays.toString(intervals));

        for (int i = 0; i < count; i++) {    // Insert the threads in the list

            List<Employee> splitEmployees = employees.subList(intervals[i*2], intervals[(i*2)+1]);

            InsertEmployeeThread insertEmployeeThread = new InsertEmployeeThread(
                    new ArrayList(splitEmployees)
            );
            threads[i] = new Thread(insertEmployeeThread);
        }
        return threads;
    }

    /**
     * A method for checking if the thread count given is valid
     *
     * @param count - Number of threads to execute inserted by the user
     * @return true or false whether the input is valid
     */
    public boolean checkThreadCount(String count) {

        int threadCount = 0;

        try {
            threadCount = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            logger.warn("Invalid thread count input, isn't a number");
            return false;
        }

        if (threadCount >= 1 && threadCount <= 100) {
            logger.info("Thread count input was valid");
            return true;
        }
        else {
            logger.warn("Invalid thread count input is not a number between 1 and 100");
            return false;
        }
    }

    /**
     * Converts the thread count inputted by the user into an integer
     *
     * @param count - thread count as a string value
     * @return thread count as an integer value
     */
    public int parseThreadCount(String count) {
        return Integer.parseInt(count);
    }

    /**
     * Closes the connection between the running program and the database
     */
    public void cleanUpDatabase() {
        try {
            ConnectionFactory.closeConnection();
        } catch (SQLException e) {
            logger.error("Error while closing the database connection");
            e.printStackTrace();
        }
    }

    /**
     * @return the amount of employee records that were read from the CSV file
     */
    public int getEmployeeCount() {
        logger.info("Amount of employee records has been displayed");
        return employeeFileReader.getEmployeesList().size();
    }

    /**
     * @return the amount of duplicate records that were read from the CSV file
     */
    public int getDuplicateCount() {
        logger.info("Amount of duplicated records has been displayed");
        return employeeFileReader.getDuplicates().size();
    }

    /**
     * @return the duplicate employee records from a list to string
     */
    public String getDuplicatesString() {
        logger.info("Duplicate records has been displayed to the user");
        return employeeFileReader.getDuplicates().toString();
    }

    /**
     * @return the amount of corrupted records that were read from the CSV file
     */
    public int getCorruptedCount() {

        int corruptCount = 0;
        ArrayList<Employee> employees = employeeFileReader.getEmployeesList();

        for (Employee employee : employees) {
            // If one of the fields of  the employee is null, then increment
            if (!employee.isRecordValid()) {
                corruptCount++;
            }
        }
        logger.info("Amount of corrupted records has been displayed");
        return corruptCount;
    }
}
