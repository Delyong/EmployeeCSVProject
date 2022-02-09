package com.sparta.employeecsvtest;

import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    private static Employee employee;

    @BeforeAll
    public static void setUp(){
        employee = new Employee();;
    }

    @DisplayName("Given date '12/19/1977', parseDate.toString returns a date string that " +
            "matches the expected date")
    @Test
    public void givenAValidDate_parseDate_returnsMatchingDate() {
        Date date = employee.parseDate("12/19/1977");
        Assertions.assertEquals("1977-12-19", date.toString());
    }

    @DisplayName("Given an invalid date '122/6/1000', parseDate returns null")
    @Test
    public void givenAInvalidDate_parseDate_returnsNull() {
        Date date = employee.parseDate("122/6/1000");
        Assertions.assertEquals(null, date);
    }

    @DisplayName("Given a null date, parseDate returns null")
    @Test
    public void givenANullDate_parseDate_returnsNull() {
        Date date = employee.parseDate(null);
        Assertions.assertEquals(null, date);
    }

    @DisplayName("Given a null name, parseName returns null")
    @Test
    public void givenANullName_parseName_returnsNull() {
        String name = employee.parseName(null);
        Assertions.assertEquals(null, name);
    }

    @DisplayName("Given name 'Roberto', parseName matches name")
    @Test
    public void givenAValidName_parseName_matchesName() {
        String name = employee.parseName("Roberto");
        Assertions.assertEquals("Roberto", name);
    }

    @DisplayName("Given an empty name, parseName returns null")
    @Test
    public void givenAnEmptyName_parseName_returnsNull() {
        String name = employee.parseName("");
        Assertions.assertEquals(null, name);
    }

    @ParameterizedTest
    @CsvSource(value = {"R,R", "X,X", "M,M"})
    @DisplayName("Given a valid middleInitial, parseMiddleInitial returns expected character")
    public void givenAValidMiddleInitial_parseMiddleInitial_matchingCharacter(
            String middleInitial, Character expected)
    {
        Character actual = employee.parseMiddleInitial(middleInitial);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Given a middleInitial 'RM', parseMiddleInitial returns null")
    @Test
    public void givenA2LengthMiddleInitial_parseMiddleInitial_returnsNull() {
        Character middleInitial = employee.parseMiddleInitial("RM");
        Assertions.assertEquals(null, middleInitial);
    }

    @DisplayName("Given a null middle initial, parseMiddleInitial returns null")
    @Test
    public void givenANullMiddleInitial_parseMiddleInitial_returnsNull() {
        Character middleInitial = employee.parseMiddleInitial(null);
        Assertions.assertEquals(null, middleInitial);
    }

    @Test
    @DisplayName("Given a valid employee ID, convertEmployeeId, should return the integer value")
    public void givenAValidEmployeeId_ConvertEmployeeId_ReturnsTheIntegerValue(){
        assertEquals(1240, employee.parseEmployeeId("1240"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12f", "test", "!%", "34af*"})
    @DisplayName("Given a invalid employee ID, convertEmployeeId, should return null")
    public void givenAnInvalidEmployeeId_ConvertEmployeeId_ReturnsNull(String employeeId){
        assertEquals(null, employee.parseEmployeeId(employeeId));
    }

    @Test
    @DisplayName("Given a valid name prefix, convertEmployeeId, should return the the string value with correct format")
    public void givenAValidNamePrefix_ConvertNamePrefix_ReturnsTheStringValue(){
        assertEquals("Mr.", employee.parseNamePrefix("Mr."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Mrrss.", "Mr", "*%&.", "Mrs13."})
    @DisplayName("Given a invalid name prefix, convertNamePrefix, should return null")
    public void givenAnInvalidNamePrefix_ConvertNamePrefix_ReturnsNull(String namePrefix){
        assertEquals(null, employee.parseNamePrefix(namePrefix));
    }

    @Test
    @DisplayName("Given a valid gender, convertGender, should return the the character value")
    public void givenAValidGender_ConvertGender_ReturnsTheCharacterValue(){
        assertEquals('F', employee.parseGender("F"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Male", "M12", "%$^", "34adf*"})
    @DisplayName("Given a invalid gender, convertGender, should return null")
    public void givenAnInvalidGender_ConvertGender_ReturnsNull(String gender){
        assertEquals(null, employee.parseGender(gender));
    }

    @Test
    @DisplayName("Given a valid email, convertEmail, should return the string valid email format")
    public void givenAValidEmail_ConvertEmail_ReturnsTheStringValue(){
        assertEquals("test@gmail.com", employee.parseEmail("test@gmail.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"notanemail", "test$%£@gmail.com", "1234asdf£", "testgmailcom"})
    @DisplayName("Given a invalid email, convertEmail, should return null")
    public void givenAnInvalidEmail_ConvertEmail_ReturnsNull(String email){
        assertEquals(null, employee.parseEmail(email));
    }

    @Test
    @DisplayName("Given a valid salary, convertSalary, should return the integer value")
    public void givenAValidSalary_ConvertSalary_ReturnsTheIntegerValue(){
        assertEquals(45000, employee.parseSalary("45000"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"12bghjue", "%$53", " ", "notanumber"})
    @DisplayName("Given a invalid employee ID, convertEmployeeId, should return null")
    public void givenAnInvalidSalary_ConvertSalary_ReturnsNull(String salary){
        assertEquals(null, employee.parseSalary(salary));
    }
}
