import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Date;

public class EmployeeTests {

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
    }

    @DisplayName("Given date '12/19/1977', parseDate.toString returns a date string that " +
            "matches the expected date")
    @Test
    public void givenAValidDate_parseDate_returnsMatchingDate() {
        Date date = employee.parseDate("12/19/1977");
        Assertions.assertEquals("1977-12-19", date.toString());
    }

    // TODO here
    @DisplayName("Given date '12/19/1977', parseDate.toString returns a date string that " +
            "matches the expected date")
    @Test
    public void givenAInvalidDate_parseDate_returnsNull() {
        Date date = employee.parseDate("12/19/1977");
        Assertions.assertEquals("1977-12-19", date.toString());
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
    @CsvSource(value = {

    })
    @DisplayName("Given a valid middleInitial, parseMiddleInitial returns expected character")
    public void givenAValidMiddleInitial_parseMiddleInitial_matchingCharacter(
            String middleInitial, Character expected)
    {
        System.out.println(middleInitial);
        System.out.println(expected);
        Character actual = employee.parseMiddleInitial(middleInitial);
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Given a null middle initial, parseMiddleInitial returns null")
    @Test
    public void givenANullMiddleInitial_parseMiddleInitial_returnsNull() {
        Character middleInitial = employee.parseMiddleInitial(null);
        Assertions.assertEquals(null, middleInitial);
    }


}
