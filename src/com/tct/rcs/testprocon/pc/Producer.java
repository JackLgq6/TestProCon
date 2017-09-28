package com.tct.rcs.testprocon.pc;

import com.tct.rcs.testprocon.factory.AbstractFactory;
import com.tct.rcs.testprocon.utils.YearUtils;

public class Producer implements Runnable {

	private int initYear = 12; // 初始年

	private AbstractFactory mAbstractFactory;
	private int year;
	private int month;
	private int day;

	public Producer() {
		super();
	}
	
	public Producer(AbstractFactory mAbstractFactory, int year, int month, int day) {
		this.mAbstractFactory = mAbstractFactory;
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Override
	public void run() {
		YearUtils.setDate(year, month, day);
		//mAbstractFactory.produce(YearUtils.getYear(), YearUtils.getMonth());
		YearUtils.go(mAbstractFactory, new Producer());
	}

}
