package com.sparta.employeecsv.model;

import java.util.ArrayList;

public class InsertEmployeeThread implements Runnable {

    private ArrayList<Employee> employees;

    public InsertEmployeeThread(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    private ArrayList<ArrayList<Employee>> splitTests(int splitSize) {

        ArrayList<ArrayList<Employee>> splitEmployees = new ArrayList<>();

        int listAmount = employees.size() / splitSize;
        int remainder = employees.size() % splitSize;

        for (int i = 0; i < listAmount; i++) {

            ArrayList<Employee> employeesList = new ArrayList<Employee>();

            for (int x = 0; x < splitSize; x++) {
                employeesList.add(employees.get((i * splitSize) + x));
            }

            splitEmployees.add(employeesList);

        }

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


    @Override
    public void run() {
        ArrayList<ArrayList<Employee>> splitEmployees = splitTests(100);
        EmployeeDatabase employeeDatabase = new EmployeeDatabase();
        // employeeDatabase.insertRecordsList(employees);
        employeeDatabase.insertRecordsMultipleList(splitEmployees);
    }

}
