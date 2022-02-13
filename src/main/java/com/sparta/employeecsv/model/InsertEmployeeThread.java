package com.sparta.employeecsv.model;

import java.util.ArrayList;

/**
 * Class which allows employee record insertions to be runnable and able to process multiple insertions at the same time
 * through the use of multithreading
 */
public class InsertEmployeeThread implements Runnable {

    private ArrayList<Employee> employees;

    /**
     * @param employees - a list of to be inserted
     */
    public InsertEmployeeThread(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Splits the list of employees based off the size of the splits
     */
    private ArrayList<ArrayList<Employee>> splitTests(int splitSize) {

        ArrayList<ArrayList<Employee>> splitEmployees = new ArrayList<>();

        int listAmount = employees.size() / splitSize;    // Gets the amount of lists the program will make
        int remainder = employees.size() % splitSize;    // Amount of leftovers that were not counted in the division

        // Insert the list of employees as long as it's in the range of the split size
        for (int i = 0; i < listAmount; i++) {

            ArrayList<Employee> employeesList = new ArrayList<Employee>();

            for (int x = 0; x < splitSize; x++) {
                employeesList.add(employees.get((i * splitSize) + x));
            }

            splitEmployees.add(employeesList);

        }

        // Add the remainders
        if (remainder > 0) {
            ArrayList<Employee> employeesList = new ArrayList<Employee>();
            for (int i = (employees.size()-remainder); i < employees.size(); i++) {
                employeesList.add(employees.get(i));
            }
            splitEmployees.add(employeesList);
        }

        int total = 0;

        for (ArrayList<Employee> list : splitEmployees) {
            total = total + list.size();
        }

        return splitEmployees;
    }


    /**
     * The method that runs for each thread when they are started
     */
    @Override
    public void run() {
        ArrayList<ArrayList<Employee>> splitEmployees = splitTests(100);
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        // employeeDatabase.insertRecordsList(employees);
        employeeDatabase.insertRecordsMultipleList(splitEmployees);
    }

}
