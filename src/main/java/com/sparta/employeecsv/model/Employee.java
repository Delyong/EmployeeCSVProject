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

    public Employee(String employeeID, String namePrefix, String firstName,
                    String middleName, String lastName, String gender,
                    String email, String dateOfBirth, String dateOfJoin,
                    String salary
    )
    {

        this.employeeID = convertEmployeeId(employeeID);

        this.firstName = firstName;
        // this.middleName = middleName;
        this.lastName = lastName;

    }

    private Integer convertEmployeeId(String employeeID) {
        return null;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

}
