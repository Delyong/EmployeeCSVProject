package com.sparta.employeecsv.model;

import com.sparta.employeecsv.CSVMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import static com.sparta.employeecsv.CSVMain.logger;

public class ReadFile {

    private HashMap<String, Employee> employees;
    private ArrayList<Employee> duplicates;

    public void readFile(String fileName) {

        employees = new HashMap<>();
        duplicates = new ArrayList<>();

        EmployeeParser employeeParser = new EmployeeParser();

        try { //read data from the file
            Files.lines(Path.of(fileName))
                    .map(s -> {
                        String[] data = s.split(",");
                        return employeeParser.parseEmployee(data[0], data[1], data[2],
                                data[3], data[4], data[5], data[6],
                                data[7], data[8], data[9]);
                    })
                    .distinct()
                    .forEach(em -> {
                        if (employees.containsKey(em.getEmployeeID())) {
                            duplicates.add(em);
                            employees.remove(em.getEmployeeID());
                        } else {
                            employees.put("" + em.getEmployeeID(), em); //add the object to the List
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*        if (employees.containsKey(e.getEmployeeID())) {
            duplicates.add(e);
            employees.remove(e.getEmployeeID());
        } else {
            employees.put("" + e.getEmployeeID(), e); //add the object to the List
        }*/

        /*try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { //read data from the file

            //list to collect Employee objects
            employees = new HashMap<String,Employee>();

            String line = "";
            while ((line = br.readLine()) != null) { //read the file line by line
                String[] data = line.split(","); //extract individual fields from each line

                //create new Employee object
                Employee employee = employeeParser.parseEmployee(data[0], data[1], data[2],
                        data[3], data[4], data[5], data[6],
                        data[7], data[8], data[9]);

                if (employees.containsKey(data[0])) {
                    duplicates.add(employee);
                    employees.remove(data[0]);
                } else {
                    employees.put(data[0], employee); //add the object to the List
                }

            }
            logger.info("File provided has been read");

        } catch (IOException e) {
            CSVMain.logger.error("Error reading the file");
            e.printStackTrace();
        }*/

    public HashMap<String, Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Employee> getEmployeeAsList() {
        ArrayList<Employee> employeesList = new ArrayList<Employee>(employees.size());

        for (Employee employee : employees.values()) {
            employeesList.add(employee);
        }

        return employeesList;
    }

    public ArrayList<Employee> getDuplicates() {
        return duplicates;
    }

}
