package com.tct.rcs.testprocon.utils;

import java.util.Calendar;

public class MonthUtils {

	static Calendar calendar = Calendar.getInstance();
	
	public static boolean isRight(int month) {
		if (month != 7 && month != 8 && month !=9) {
			return true;
		}
		return false;
	}
	
	public static boolean isEggsMonth(int month) {
		if (month == 1 || month == 3 || month == 5 || month == 10 || month == 12) {
			return true;
		}
		return false;
	}
	
	public static boolean isOtherEggsMonth(int month) {
		if (month == 4 || month == 6 || month == 11) {
			return true;
		}
		return false;
	}
	
	public static boolean isThirdMonth(int month, int day) {
		if (3 == month && day == 22) {
			return true;
		}
		return false;
	}
	
	public static boolean isSencondMonth(int month) {
		if (2 == month) {
			return true;
		}
		return false;
	}
	
	public static void daysAfter(int month, int day) {
		calendar.set(Calendar.DAY_OF_MONTH, day + 120);
		calendar.set(Calendar.MONTH, month);
	}
	
	public void fourMonthAfter(int month) {
		calendar.set(Calendar.MONTH, month + 4);
	}
	
	public static void oneYearAfter(int year) {
		calendar.set(Calendar.YEAR, year + 1);
	}
	
	public static void timeChange(int year, int month) {
		calendar.set(Calendar.MONTH, ++month);
		calendar.set(Calendar.YEAR, year);
	}
	
	public static void main(String[] args) {
		MonthUtils m = new MonthUtils();
		/*m.fourMonthAfter(9);
		System.out.println(m.calendar.get(Calendar.MONTH));
		m.oneYearAfter(2017);
		System.out.println(m.calendar.get(Calendar.YEAR));*/
		m.daysAfter(3, 22);
		System.out.println(m.calendar.get(Calendar.MONTH));
		System.out.println(m.calendar.get(Calendar.DAY_OF_MONTH));
	}
	
}
