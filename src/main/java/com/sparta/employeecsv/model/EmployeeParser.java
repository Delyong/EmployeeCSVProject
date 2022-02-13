package com.sparta.employeecsv.model;

import java.sql.Date;

/**
 * Class in charged of converting all the employee fields as string value taken from employee file reader
 * into the correct data types through checks (regex) and casting
 */
public class EmployeeParser {

    /**
     * Basic constructor with the same attributes as an employee but with parsed values
     * @param employeeID
     * @param namePrefix
     * @param firstName
     * @param middleInitial
     * @param lastName
     * @param gender
     * @param email
     * @param dateOfBirth
     * @param dateOfJoin
     * @param salary
     * @return
     */
    public Employee parseEmployee(String employeeID, String namePrefix, String firstName,
                                  String middleInitial, String lastName, String gender,
                                  String email, String dateOfBirth, String dateOfJoin,
                                  String salary) {
        Employee employee;

        employee = new Employee(
                parseEmployeeId(employeeID), parseNamePrefix(namePrefix),
                parseName(firstName), parseMiddleInitial(middleInitial),
                parseName(lastName), parseGender(gender),
                parseEmail(email), parseDate(dateOfBirth),
                parseDate(dateOfJoin), parseSalary(salary)
        );
        return employee;
    }

    /**
     * Check if the name is valid
     */
    public String parseName(String name) {
        if (name == null || name.length() == 0) {
            return null;
        }
        return name;
    }

    /**
     * Check if the initial is valid length
     */
    public Character parseMiddleInitial(String middleInitial) {

        if (middleInitial == null || middleInitial.length() != 1) {
            return null;
        }
        return middleInitial.charAt(0);
    }

    /**
     * Check of the date's validation
     */
    public Date parseDate(String date) {

        if (date == null) {
            return null;
        }

        // check length of date
        StringBuilder sb = new StringBuilder();
        String[] dateSplitArray = date.split("/");

        // if the array doesn't have 3 elements an error will be thrown
        if (dateSplitArray.length != 3) {
            return null;
        }

        sb.append(dateSplitArray[2]).append("-")
                .append(dateSplitArray[0]).append("-")
                .append(dateSplitArray[1]);

        try {
            return Date.valueOf(sb.toString());
        } catch (IllegalArgumentException e) {
            return null;
        }

    }

    /**
     * A validation of the employee ID using regex
     */
    public Integer parseEmployeeId(String employeeID) {
        if (employeeID.matches("[0-9]{1,10}"))
            return Integer.valueOf(employeeID);
        else
            return null;
    }

    /**
     * A validation of the name prefix through regex
     */
    public String parseNamePrefix(String namePrefix) {
        if (namePrefix.matches("[a-zA-Z]{2,4}."))
            return namePrefix;
        else
            return null;
    }

    /**
     * A validation of the gender though regex
     */
    public Character parseGender(String gender) {
        if (gender.matches("F|M"))
            return gender.charAt(0);
        else
            return null;
    }

    /**
     * A validation of the email with a complicated regex, where it checks it follows the correct format
     */
    public String parseEmail(String email) {
        if (email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
            return email;
        else
            return null;
    }

    /**
     * A validation for the salary, making sure it has the correct digits
     */
    public Float parseSalary(String salary) {
        if (salary.matches("[0-9]{1,10}"))
            return Float.valueOf(salary);
        else
            return null;
    }
}
