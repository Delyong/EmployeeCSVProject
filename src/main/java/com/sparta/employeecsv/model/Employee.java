package com.sparta.employeecsv.model;

import java.sql.Date;
import java.util.Objects;

/**
 * A class which represents each employee that will be read from the CSV file.
 * Including all the attributes and methods it should include
 */
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

    /**
     * Basic constructor which takes in all the necessary attributes as a paramater
     * And initialises them
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
     */
    public Employee(Integer employeeID, String namePrefix, String firstName,
                    Character middleInitial, String lastName, Character gender,
                    String email, Date dateOfBirth, Date dateOfJoin,
                    Float salary
    ) {

        this.employeeID = employeeID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoin = dateOfJoin;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
    }

    /**
     * Default constructor
     */
    public Employee() {
        super();
    }

    // Getters

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

    // Setters

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(Character middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    /**
     * A check whether the employee record is corrupted
     * @return true or false if the employee is corrupted
     */
    public boolean isRecordValid() {
        if (employeeID == null || namePrefix == null || firstName == null ||
                middleInitial == null || lastName == null || gender == null ||
                email == null || dateOfBirth == null || dateOfJoin == null ||
                salary == null) {
            return false;
        }
        return true;
    }

    /**
     * A non-generated employee toString method to display the employee details in a cleaner way
     * @return String value of employee
     */
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
                " | salary: $" + salary + ")\n";
    }

    /**
     * Generated equals method
     * @param o - Object to be compared to
     * @return true or false if the 2 objects have the same primary key or employee ID
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeID.equals(employee.employeeID);
    }

    /**
     * Generated hashcode method
     * @return hashcode value of the employee ID
     */
    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }
}
