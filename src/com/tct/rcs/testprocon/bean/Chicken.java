package com.tct.rcs.testprocon.bean;

import com.tct.rcs.testprocon.factory.AbstractFactory;
import com.tct.rcs.testprocon.factory.ChickenFactory;

public class Chicken extends Animal {

	private long mEggs; //下蛋数量
	
	public static final int InitYear = 12;	//初始年:12年
	public static final int SellMonth = 2;	//出售月份
	public static final int BirthMonth = 3; //出生月份
	private int mCurrentYear = 2017;	//当前年
	private int mCurrentMonth = 9;	//当前月
	private int CurrentDay = 1;	//当前日
	private int day;
	AbstractFactory factory = new ChickenFactory();
	
	@Override
	public void giveBirth() {
		factory.produce(mCurrentYear, mCurrentMonth, day);
	}

	public void custome() {
		factory.consume(mCurrentYear, mCurrentMonth);
	}
	
	public long getmEggs() {
		return mEggs;
	}

	public void setmEggs(long mEggs) {
		this.mEggs = mEggs;
	}
	
	public static void main(String[] args) {
		Chicken c = new Chicken();
		c.giveBirth();
		c.custome();
	}
   
}
