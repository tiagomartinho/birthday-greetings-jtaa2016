package it.xpug.kata.birthday_greetings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class XDate {

	private Date date;

	public XDate() {
		date = new Date();
	}

	public XDate(String yyyyMMdd) throws ParseException {
		date = new SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd);
	}

	public int getDay() {
		return getPartOfDate(GregorianCalendar.DAY_OF_MONTH);
	}

	public int getMonth() {
		return 1 + getPartOfDate(GregorianCalendar.MONTH);
	}

	public int getYear() {
		return getPartOfDate(GregorianCalendar.YEAR);
	}

	public boolean isSameDay(XDate anotherDate) {
		if (!isLeapYear() && (isFebruaryTwentyNine() || anotherDate.isFebruaryTwentyNine()) ) {
			return isFirstMarch() || anotherDate.isFirstMarch();
		} else {
			return sameDayAndMonth(anotherDate);
		}
	}

	private boolean isFirstMarch() {
		return getDay() == 1 && getMonth() == 3;
	}

	private boolean isFebruaryTwentyNine() {
		return getDay() == 29 && getMonth() == 2;
	}

	private boolean sameDayAndMonth(XDate anotherDate) {
		return anotherDate.getDay() == this.getDay() && anotherDate.getMonth() == this.getMonth();
	}

	@Override
	public int hashCode() {
		return date.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof XDate))
			return false;
		XDate other = (XDate) obj;
		return other.date.equals(this.date);
	}

	private int getPartOfDate(int part) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(part);
	}

	public boolean isLeapYear() {
		return new Date(this.getYear(), 1, 29).getMonth() == 1;
	}
}
