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

    public static Integer convertEmployeeId(String employeeID) {
        if (employeeID.matches("[0-9]{1,5}"))
            return Integer.valueOf(employeeID);
        else
            return null;
    }

    public static String convertNamePrefix(String namePrefix){
        if (namePrefix.matches("[a-zA-Z]{2,3}."))
            return namePrefix;
        else
            return null;
    }

    public static Character convertGender(String gender){
        if (gender.matches("F|M"))
            return gender.charAt(0);
        else
            return null;
    }

    public static String convertEmail(String email){
        if (email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
            return email;
        else
            return null;
    }

    public static Float convertSalary(String salary){
        if (salary.matches("[0-9]{1,10}"))
            return Float.valueOf(salary);
        else
            return null;
    }
}
