package com.sparta.employeecsv.model;

import com.sparta.employeecsv.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.toList;

public class ReadFile {
    String fileName = "EmployeeRecords.csv";
    public static Logger logger = LogManager.getLogger("Project Logger");
    public void readFile(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { //read data from the file
            List<Employee> employees = new ArrayList<Employee>(); //list to collect Employee objects

            String line = "";
            while ((line = br.readLine()) != null) { //read the file line by line
                String[] data = line.split(","); //extract individual fields from each line

                Employee employee = new Employee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]); //create new Employee object

                employees.add(employee); //add the object to the List
            }

        } catch (IOException e) {
            logger.error("Error reading the file");
            e.printStackTrace();
        }
    }

    public static List<Employee> getDuplicates(final List<Employee> employeeList){

        List<Employee> duplicates = employeeList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(n -> n.getValue() > 1)
                .flatMap(n -> nCopies(n.getValue().intValue(), n.getKey()).stream())
                .collect(toList());

        return duplicates;

    }
}
