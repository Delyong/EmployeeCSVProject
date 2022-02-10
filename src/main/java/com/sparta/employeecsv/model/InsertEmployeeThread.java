package com.sparta.employeecsv.model;

import java.sql.Connection;
import java.util.ArrayList;

public class InsertEmployeeThread implements Runnable {

    private ArrayList<Employee> employees;

    public InsertEmployeeThread(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public void run() {
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        employeeDatabase.insertRecordsList(employees);
    }

}
