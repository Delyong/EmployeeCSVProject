package com.sparta.employeecsv.model;

public class Employee {

    private Integer employeeID;

    private String namePrefix;
    private String firstName;
    private Character middleInitial;
    private String lastName;

    private Character gender;
    private String email;
    private String dateOfBirth;
    private String dateOfJoin;

    private Float salary;
    private boolean isValidRecord;

    public Employee(String employeeID, String namePrefix, String firstName,
                    String middleName, String lastName, String gender,
                    String email, String dateOfBirth, String dateOfJoin,
                    String salary
    )
    {

        this.employeeID = convertEmployeeId(employeeID);

        this.firstName = firstName;
        this.middleInitial = parseMiddleName("s");
        this.lastName = lastName;

    }

    private Character parseMiddleName(String middleInitial) {

        if (middleInitial.length() != 1) {
            return null;
        }

        return middleInitial.charAt(0);
    }

    private Integer convertEmployeeId(String employeeID) {
        return null;
    }

    public boolean isRecordValid() {
        return false;
    }



}
