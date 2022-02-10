package com.sparta.employeecsv.model;

import java.sql.Connection;
import java.util.ArrayList;

public class InsertEmployeeThread implements Runnable {

    private ArrayList<Employee> employees;
    private Connection connection;

    public InsertEmployeeThread(Connection connection, ArrayList<Employee> employees) {
        this.connection = connection;
        this.employees = employees;
    }

    @Override
    public void run() {
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        employeeDatabase.insertRecordsList(connection, employees);
    }

}
