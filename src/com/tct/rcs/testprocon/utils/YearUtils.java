package com.tct.rcs.testprocon.utils;

import java.util.Calendar;

import com.tct.rcs.testprocon.factory.AbstractFactory;

public class YearUtils {

	private static final Calendar mCalendar = Calendar.getInstance();

	public static boolean isLeapYear(int year) {
		if (year % 4 == 0) {
			return true;
		}
		return false;
	}

	public static void setDate(int year, int month, int day) {
		mCalendar.set(Calendar.YEAR, year);
		mCalendar.set(Calendar.MONTH, month);
		mCalendar.set(Calendar.DAY_OF_MONTH, day);
	}

	public static int getYear() {
		int year = mCalendar.get(Calendar.YEAR);
		return year;
	}
	
	public static int getMonth() {
		int month = mCalendar.get(Calendar.MONTH);
		return month;
	}
	
	public static void go(AbstractFactory abstractFactory) {
		int year = mCalendar.get(Calendar.YEAR);
		int month = mCalendar.get(Calendar.MONTH);
		int day = mCalendar.get(Calendar.DAY_OF_MONTH);
		/*if (object instanceof Producer) {
			abstractFactory.produce(year, month);
		} else if (object instanceof Consumer) {
			abstractFactory.consume(year, month);
		}*/
		abstractFactory.produce(year, month, day);
		abstractFactory.consume(year, month);
		/*oneDayAgo(day);
		if (day == 30) {
			oneMouthAgo(month);
		}
		if (month == Calendar.DECEMBER
				&& day == mCalendar.getActualMaximum(Calendar.DATE)) {
			oneYearAgo(year);
		}*/

		mCalendar.set(Calendar.DAY_OF_MONTH, ++day);

		if (isEnd(year, month, day)) {
			return;
		}

		go(abstractFactory);
	}

	public static void oneDayAgo(int day) {
		//mCalendar.set(Calendar.DAY_OF_MONTH, day);
	}

	public static void oneMouthAgo(int month) {
		//mCalendar.set(Calendar.MONTH, month);
	}

	public static void oneYearAgo(int year) {
		//mCalendar.set(Calendar.YEAR, year);
	}

	public static boolean isEnd(int year, int month, int day) {
		if (year == 2029 && month == 8 && day == 1) {
			return true;
		}
		return false;
	}
}
