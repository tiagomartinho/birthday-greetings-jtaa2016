package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import org.junit.*;



public class XDateTest {
	@Test
	public void getters() throws Exception {
		XDate date = new XDate("1789/01/24");
		assertEquals(1, date.getMonth());
		assertEquals(24, date.getDay());
	}

	@Test
	public void isSameDate() throws Exception {
		XDate date = new XDate("1789/01/24");
		XDate sameDay = new XDate("2001/01/24");
		XDate notSameDay = new XDate("1789/01/25");
		XDate notSameMonth = new XDate("1789/02/25");

		assertTrue("same", date.isSameDay(sameDay));
		assertFalse("not same day", date.isSameDay(notSameDay));
		assertFalse("not same month", date.isSameDay(notSameMonth));
	}

	@Test
	public void equality() throws Exception {
		XDate base = new XDate("2000/01/02");
		XDate same = new XDate("2000/01/02");
		XDate different = new XDate("2000/01/04");

		assertFalse(base.equals(null));
		assertFalse(base.equals(""));
		assertTrue(base.equals(base));
		assertTrue(base.equals(same));
		assertFalse(base.equals(different));
	}

	@Test
	public void isNotLeapYear() throws Exception {
		XDate firstMarch =  new XDate("2015/03/01");
		assertFalse(firstMarch.isLeapYear());
	}

	@Test
	public void isLeapYear() throws Exception {
		XDate firstMarch =  new XDate("2016/03/01");
		assertTrue(firstMarch.isLeapYear());
	}

	@Test
	public void isEaster() throws Exception {
		XDate easter =  new XDate("2016/03/27");
		assertTrue(easter.isEaster());
		XDate anotherEaster =  new XDate("2015/04/05");
		assertTrue(anotherEaster.isEaster());
	}

	@Test
	public void isNotEaster() throws Exception {
		XDate notEaster =  new XDate("2016/03/01");
		assertFalse(notEaster.isEaster());
		XDate anotherNotEaster =  new XDate("2015/04/01");
		assertFalse(anotherNotEaster.isEaster());
	}
}
