package com.sparta.employeecsv.model;

import java.sql.Date;

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

    public Employee(String employeeID, String namePrefix, String firstName,
                    String middleInitial, String lastName, String gender,
                    String email, String dateOfBirth, String dateOfJoin,
                    String salary
    ) {

        this.employeeID = EmployeeParser.parseEmployeeId(employeeID);

        this.firstName = EmployeeParser.parseName(firstName);
        this.middleInitial = EmployeeParser.parseMiddleInitial(middleInitial);
        this.lastName = EmployeeParser.parseName(lastName);

        this.dateOfBirth = EmployeeParser.parseDate(dateOfBirth);
        this.dateOfJoin = EmployeeParser.parseDate(dateOfJoin);
        this.namePrefix = EmployeeParser.parseNamePrefix(namePrefix);

        this.gender = EmployeeParser.parseGender(gender);
        this.email = EmployeeParser.parseEmail(email);
        this.salary = EmployeeParser.parseSalary(salary);
    }

    public Employee() {
        super();
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public Character getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public Character getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public Float getSalary() {
        return salary;
    }

    public boolean isRecordValid() {

        if (employeeID == null || namePrefix == null || firstName == null ||
                middleInitial == null || lastName == null || gender == null ||
                email == null || dateOfBirth == null || dateOfJoin == null ||
                salary == null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "(employeeID: " + employeeID +
                " | namePrefix: " + namePrefix +
                " | firstName: " + firstName +
                " | middleInitial: " + middleInitial +
                " | lastName: " + lastName +
                " | gender: " + gender +
                " | email: " + email +
                " | dob: " + dateOfBirth +
                " | doj: " + dateOfJoin +
                " | salary: " + salary + ")\n";
    }
}
