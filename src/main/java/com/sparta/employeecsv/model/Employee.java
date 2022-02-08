package com.sparta.employeecsv.model;

public class Employee{

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