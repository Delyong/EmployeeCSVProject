import com.sparta.employeecsv.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class EmployeeTests {

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee();
    }

    @Test
    public void test() {
        Date date = employee.parseDate("12/19/1977");
        Assertions.assertEquals("1977-12-19", date.toString());
    }



}
