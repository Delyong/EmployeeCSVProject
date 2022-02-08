package com.sparta.employeecsvtest;

import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    private static Employee employee;

    @BeforeAll
    public static void setUp(){
        employee = new Employee("1", "Mrs.", "John", "A", "Smith", "F", "lavon.shufelt@aol.com", "12/19/1977","7/23/2000", "184597");
    }

    @Test
    @DisplayName("Given a valid employee ID, convertEmployeeId, should return the integer value")
    public void givenAValidEmployeeId_ConvertEmployeeId_ReturnsTheIntegerValue(){
        assertEquals(1240, employee.convertEmployeeId("1240"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12f", "test", "!%", "34af*"})
    @DisplayName("Given a invalid employee ID, convertEmployeeId, should return null")
    public void givenAnInvalidEmployeeId_ConvertEmployeeId_ReturnsNull(String employeeId){
        assertEquals(null, employee.convertEmployeeId(employeeId));
    }

    @Test
    @DisplayName("Given a valid name prefix, convertEmployeeId, should return the the string value with correct format")
    public void givenAValidNamePrefix_ConvertNamePrefix_ReturnsTheStringValue(){
        assertEquals("Mr.", employee.convertNamePrefix("Mr."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Mrrss.", "Mr", "*%&.", "Mrs13."})
    @DisplayName("Given a invalid name prefix, convertNamePrefix, should return null")
    public void givenAnInvalidNamePrefix_ConvertNamePrefix_ReturnsNull(String namePrefix){
        assertEquals(null, employee.convertNamePrefix(namePrefix));
    }

    @Test
    @DisplayName("Given a valid gender, convertGender, should return the the character value")
    public void givenAValidGender_ConvertGender_ReturnsTheCharacterValue(){
        assertEquals('F', employee.convertGender("F"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Male", "M12", "%$^", "34adf*"})
    @DisplayName("Given a invalid gender, convertGender, should return null")
    public void givenAnInvalidGender_ConvertGender_ReturnsNull(String gender){
        assertEquals(null, employee.convertGender(gender));
    }

    @Test
    @DisplayName("Given a valid email, convertEmail, should return the string valid email format")
    public void givenAValidEmail_ConvertEmail_ReturnsTheStringValue(){
        assertEquals("test@gmail.com", employee.convertEmail("test@gmail.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"notanemail", "test$%£@gmail.com", "1234asdf£", "testgmailcom"})
    @DisplayName("Given a invalid email, convertEmail, should return null")
    public void givenAnInvalidEmail_ConvertEmail_ReturnsNull(String email){
        assertEquals(null, employee.convertEmail(email));
    }

    @Test
    @DisplayName("Given a valid salary, convertSalary, should return the integer value")
    public void givenAValidSalary_ConvertSalary_ReturnsTheIntegerValue(){
        assertEquals(45000, employee.convertSalary("45000"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"12bghjue", "%$53", " ", "notanumber"})
    @DisplayName("Given a invalid employee ID, convertEmployeeId, should return null")
    public void givenAnInvalidSalary_ConvertSalary_ReturnsNull(String salary){
        assertEquals(null, employee.convertSalary(salary));
    }
}
