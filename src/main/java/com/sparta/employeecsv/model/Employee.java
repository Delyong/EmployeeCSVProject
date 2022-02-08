package com.sparta.employeecsv.model;

<<<<<<< HEAD
public class Employee{
=======
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
>>>>>>> 7249e4d43da2fa7b400ba9184976d2445f9af229

    private Integer id;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastname;
    private String gender;
    private String email;
    private String dob;
    private String dateOfJoining;
    private String salary;

    public Employee(){
    }

    public Employee(Integer id, String namePrefix, String firstName, String middleInitial, String lastName, String gender, String email, String dob, String dateOfJoining, String salary){
        this.id = id;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.lastname = lastName;
        this.middleInitial = middleInitial;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.dateOfJoining = dob;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

}