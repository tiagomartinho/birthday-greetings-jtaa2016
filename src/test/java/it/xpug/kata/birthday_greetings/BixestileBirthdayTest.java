package it.xpug.kata.birthday_greetings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BixestileBirthdayTest {

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        employee = new Employee("foo", "bar", "1976/02/29", "a@b.c", "+1234567890");
    }

    @Test
    public void
    bixestileBirthdayEmployeeInNonBissextileDate() throws Exception {
        XDate firstMarch =  new XDate("2015/03/01");
        assertTrue(employee.isBirthday(firstMarch));
    }

    @Test
    public void
    bixestileBirthdayEmployeeInNonBirthdayDate() throws Exception {
        XDate twentyEightFebruary = new XDate("2015/02/28");
        assertFalse(employee.isBirthday(twentyEightFebruary));
    }

    @Test
    public void
    bixestileBirthdayEmployeeInBissextileDate() throws Exception {
        XDate twentyNineFebruary = new XDate("2016/02/29");
        assertTrue(employee.isBirthday(twentyNineFebruary));
    }
}
