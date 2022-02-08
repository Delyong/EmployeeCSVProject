package com.sparta.employeecsv.model;

import java.sql.Date;
import java.util.Arrays;

public class Employee {

    private Integer employeeID;

    private String namePrefix;
    private String firstName;
    private Character middleInitial;
    private String lastName;

    private Character gender;
    private String email;
    private Date dateOfBirth;
    private Date dateOfJoin;

    private Float salary;
    private boolean isValidRecord;

    public Employee(String employeeID, String namePrefix, String firstName,
                    String middleInitial, String lastName, String gender,
                    String email, String dateOfBirth, String dateOfJoin,
                    String salary
    )
    {

        this.employeeID = convertEmployeeId(employeeID);

        this.firstName = parseName(firstName);
        this.middleInitial = parseMiddleName(middleInitial);
        this.lastName = parseName(lastName);


        this.dateOfBirth = parseDate(dateOfBirth);
        this.dateOfJoin = parseDate(dateOfJoin);


    }

    public Employee() {
        super();
    }

    private String parseName(String name) {
        if (name.length() == 0 || name == null) {
            return null;
        }

        return name;
    }

    private Character parseMiddleName(String middleInitial) {

        if (middleInitial.length() != 1 || middleInitial == null) {
            return null;
        }

        return middleInitial.charAt(0);
    }

    // require yyyy-mm-dd format
    // now mm-dd-yyyy
    public Date parseDate(String date) {

        if (date == null) { return null; }

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

    private Integer convertEmployeeId(String employeeID) {
        return null;
    }

    public boolean isRecordValid() {
        return false;
    }



}
