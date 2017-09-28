package com.tct.rcs.testprocon.pc;

import java.util.ArrayList;
import java.util.List;

import com.tct.rcs.testprocon.bean.Chicken;
import com.tct.rcs.testprocon.factory.ChickenFactory;
import com.tct.rcs.testprocon.storage.Storage;
import com.tct.rcs.testprocon.utils.YearUtils;

public class MainFarm {

	public static void main(String[] args) {
		
		//生产的年数
		int mInitYear = 2017;
		//起始月份
		int mInitMonth = 9;
		
		int day = 1;
		
		//YearUtils.go(new ChickenFactory(), );
		
		//鸡的生产/消费
		//BigDecimal mInitChickenNum = new BigDecimal("40");
//		Chicken mChicken = new Chicken();
		Storage storage = Storage.getInstance();
		ArrayList<Chicken> chickenList = new ArrayList<Chicken>();
		for (int i = 0; i < 40; i++) {
			Chicken c = new Chicken();
			c.mAge = 1;
			c.mGender = "female";
			chickenList.add(c);
		}
		for (int i = 0; i < 20; i++) {
			Chicken c = new Chicken();
			c.mAge = 1;
			c.mGender = "male";
			chickenList.add(c);
		}
		storage.setmChickenList(chickenList);
	
		/*new Producer(new ChickenFactory(), mInitYear, mInitMonth, day).run();
		new Consumer(new ChickenFactory(), mInitYear, mInitMonth, day).run();*/
		
		new Thread(new Producer(new ChickenFactory(), mInitYear, mInitMonth, day)).start();
		new Thread(new Consumer(new ChickenFactory(), mInitYear, mInitMonth, day)).start();
		
		//羊的生产/消费
		/*int mInitRawNum = 2;
		int mInitEweNum = 8;
		Sheep mSheep = new Sheep();
		new Thread(new Producer(mSheep)).start();
		new Thread(new Consumer(mSheep)).start();
				
		
		//牛的生产/消费
		int mInitBullNum = 1;
		int mInitBossyNum = 1;
		Cattle mCattle = new Cattle();
		new Thread(new Producer(mCattle)).start();*/
		
	}

}
