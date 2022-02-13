package com.sparta.employeecsvtest;

import com.sparta.employeecsv.model.EmployeeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeParserTest {

    private static EmployeeParser employeeParser;

    @BeforeAll
    public static void setUp(){
        employeeParser = new EmployeeParser();
    }

    @DisplayName("Given date '12/19/1977', parseDate.toString returns a date string that " +
            "matches the expected date")
    @Test
    public void givenAValidDate_parseDate_returnsMatchingDate() {
        Date date = employeeParser.parseDate("12/19/1977");
        Assertions.assertEquals("1977-12-19", date.toString());
    }

    @DisplayName("Given an invalid date '122/6/1000', parseDate returns null")
    @Test
    public void givenAInvalidDate_parseDate_returnsNull() {
        Date date = employeeParser.parseDate("122/6/1000");
        Assertions.assertEquals(null, date);
    }

    @DisplayName("Given a null date, parseDate returns null")
    @Test
    public void givenANullDate_parseDate_returnsNull() {
        Date date = employeeParser.parseDate(null);
        Assertions.assertEquals(null, date);
    }

    @DisplayName("Given a null name, parseName returns null")
    @Test
    public void givenANullName_parseName_returnsNull() {
        String name = employeeParser.parseName(null);
        Assertions.assertEquals(null, name);
    }

    @DisplayName("Given name 'Roberto', parseName matches name")
    @Test
    public void givenAValidName_parseName_matchesName() {
        String name = employeeParser.parseName("Roberto");
        Assertions.assertEquals("Roberto", name);
    }

    @DisplayName("Given an empty name, parseName returns null")
    @Test
    public void givenAnEmptyName_parseName_returnsNull() {
        String name = employeeParser.parseName("");
        Assertions.assertEquals(null, name);
    }

    @ParameterizedTest
    @CsvSource(value = {"R,R", "X,X", "M,M"})
    @DisplayName("Given a valid middleInitial, parseMiddleInitial returns expected character")
    public void givenAValidMiddleInitial_parseMiddleInitial_matchingCharacter(
            String middleInitial, Character expected)
    {
        Character actual = employeeParser.parseMiddleInitial(middleInitial);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Given a middleInitial 'RM', parseMiddleInitial returns null")
    @Test
    public void givenA2LengthMiddleInitial_parseMiddleInitial_returnsNull() {
        Character middleInitial = employeeParser.parseMiddleInitial("RM");
        Assertions.assertEquals(null, middleInitial);
    }

    @DisplayName("Given a null middle initial, parseMiddleInitial returns null")
    @Test
    public void givenANullMiddleInitial_parseMiddleInitial_returnsNull() {
        Character middleInitial = employeeParser.parseMiddleInitial(null);
        Assertions.assertEquals(null, middleInitial);
    }

    @Test
    @DisplayName("Given a valid employee ID, parseEmployeeId, should return the integer value")
    public void givenAValidEmployeeId_parseEmployeeId_ReturnsTheIntegerValue(){
        assertEquals(1240, employeeParser.parseEmployeeId("1240"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12f", "test", "!%", "34af*"})
    @DisplayName("Given a invalid employee ID, parseEmployeeId, should return null")
    public void givenAnInvalidEmployeeId_parseEmployeeId_ReturnsNull(String employeeId){
        assertEquals(null, employeeParser.parseEmployeeId(employeeId));
    }

    @Test
    @DisplayName("Given a valid name prefix, parseEmployeeId, should return the the string value with correct format")
    public void givenAValidNamePrefix_parseNamePrefix_ReturnsTheStringValue(){
        assertEquals("Mr.", employeeParser.parseNamePrefix("Mr."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Mrrss.", "Mr", "*%&.", "Mrs13."})
    @DisplayName("Given a invalid name prefix, parseNamePrefix, should return null")
    public void givenAnInvalidNamePrefix_parseNamePrefix_ReturnsNull(String namePrefix){
        assertEquals(null, employeeParser.parseNamePrefix(namePrefix));
    }

    @Test
    @DisplayName("Given a valid gender, parseGender, should return the the character value")
    public void givenAValidGender_parseGender_ReturnsTheCharacterValue(){
        assertEquals('F', employeeParser.parseGender("F"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Male", "M12", "%$^", "34adf*"})
    @DisplayName("Given a invalid gender, parseGender, should return null")
    public void givenAnInvalidGender_parseGender_ReturnsNull(String gender){
        assertEquals(null, employeeParser.parseGender(gender));
    }

    @Test
    @DisplayName("Given a valid email, parseEmail, should return the string valid email format")
    public void givenAValidEmail_parseEmail_ReturnsTheStringValue(){
        assertEquals("test@gmail.com", employeeParser.parseEmail("test@gmail.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"notanemail", "test$%£@gmail.com", "1234asdf£", "testgmailcom"})
    @DisplayName("Given a invalid email, parseEmail, should return null")
    public void givenAnInvalidEmail_parseEmail_ReturnsNull(String email){
        assertEquals(null, employeeParser.parseEmail(email));
    }

    @Test
    @DisplayName("Given a valid salary, parseSalary, should return the integer value")
    public void givenAValidSalary_parseSalary_ReturnsTheIntegerValue(){
        assertEquals(45000, employeeParser.parseSalary("45000"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"12bghjue", "%$53", " ", "notanumber"})
    @DisplayName("Given a invalid employee ID, parseEmployeeId, should return null")
    public void givenAnInvalidSalary_parseSalary_ReturnsNull(String salary){
        assertEquals(null, employeeParser.parseSalary(salary));
    }
}
