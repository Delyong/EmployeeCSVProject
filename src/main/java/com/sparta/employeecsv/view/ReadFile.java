package com.sparta.employeecsv.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class ReadFile {
    String fileName = "EmployeeRecords.csv";
    public void readFile(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { //read data from the file
            List<Employee> employees = new ArrayList<Employee>(); //list to collect Employee objects

            String line = "";
            while ((line = br.readLine()) != null) { //read the file line by line
                String[] data = line.split(","); //extract individual fields from each line

                Employee employee = new Employee(Integer.valueOf(data[0]), data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], Integer.valueOf(data[9])); //create new Employee object

                employees.add(employee); //add the object to the List
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> getDuplicates(final List<Employee> duplicatesList){
        return duplicatesList.stream().filter(duplicateByKey(Employee::getId))
                .collect(Collectors.toList());
    }

    private static <T> Predicate<T> duplicateByKey(final Function<? super T, Object> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> isNull(seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE));

    }
}
