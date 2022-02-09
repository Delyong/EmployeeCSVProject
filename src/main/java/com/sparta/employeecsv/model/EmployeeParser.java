package com.sparta.employeecsv.model;

import java.sql.Date;

public class EmployeeParser {
    // TODO limit string size to sql limit
    public static String parseName(String name) {
        if (name == null || name.length() == 0) {
            return null;
        }

        return name;
    }

    public static Character parseMiddleInitial(String middleInitial) {

        if (middleInitial == null || middleInitial.length() != 1) {
            return null;
        }

        return middleInitial.charAt(0);
    }

    // require yyyy-mm-dd format
    // now mm-dd-yyyy
    public static Date parseDate(String date) {

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

    public static Integer parseEmployeeId(String employeeID) {
        if (employeeID.matches("[0-9]{1,10}"))
            return Integer.valueOf(employeeID);
        else
            return null;
    }


    public static String parseNamePrefix(String namePrefix) {
        if (namePrefix.matches("[a-zA-Z]{2,4}."))
            return namePrefix;
        else
            return null;
    }

    public static Character parseGender(String gender) {
        if (gender.matches("F|M"))
            return gender.charAt(0);
        else
            return null;
    }

    public static String parseEmail(String email) {
        if (email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
            return email;
        else
            return null;
    }

    public static Float parseSalary(String salary) {
        if (salary.matches("[0-9]{1,10}"))
            return Float.valueOf(salary);
        else
            return null;
    }
}
