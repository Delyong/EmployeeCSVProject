package com.sparta.employeecsv.model;

public class Employee {

    private Integer employeeID;

    private String namePrefix;
    private String firstName;
    private String middleName;
    private String lastName;

    private char gender;
    private String email;
    private String dateOfBirth;
    private String dateOfJoin;

    private float salary;


    public Employee(String employeeID, String namePrefix, String firstName,
                    String middleName, String lastName, String gender,
                    String email, String dateOfBirth, String dateOfJoin,
                    String salary
    )
    {

        this.employeeID = Integer.parseInt(employeeID);


    }

}
