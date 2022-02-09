package com.sparta.employeecsv.model;

import com.sparta.employeecsv.view.CSVMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadFile {

    public static HashMap<String, Employee> employees;
    private ArrayList<Employee> duplicates;

    public void readFile(String fileName){

        employees = new HashMap<>();
        duplicates = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { //read data from the file
            employees = new HashMap<String,Employee>(); //list to collect Employee objects

            String line = "";
            while ((line = br.readLine()) != null) { //read the file line by line
                String[] data = line.split(","); //extract individual fields from each line

                Employee employee = new Employee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]); //create new Employee object

                if (employees.containsKey(data[0])) {
                    duplicates.add(employee);
                    employees.remove(data[0]);
                } else {
                    employees.put(data[0], employee); //add the object to the List
                }

            }

        } catch (IOException e) {
            CSVMain.logger.error("Error reading the file");
            e.printStackTrace();
        }

    }

    public HashMap<String, Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Employee> getDuplicates() {
        return duplicates;
    }
}