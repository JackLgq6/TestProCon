package com.tct.rcs.testprocon.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tct.rcs.testprocon.bean.Chicken;
import com.tct.rcs.testprocon.pc.Consumer;
import com.tct.rcs.testprocon.pc.Producer;
import com.tct.rcs.testprocon.storage.Storage;
import com.tct.rcs.testprocon.utils.MonthUtils;
import com.tct.rcs.testprocon.utils.YearUtils;

/*鸡:
 1)    其中母鸡40只，公鸡10只。每只母鸡除了7、8、9月不下蛋之外，其余时间每天下1颗蛋。
 2)    每天3月第一天开始，农夫会人工孵化鸡蛋剩总数的20%，孵蛋21天，小鸡出生且性别随机，120天后小鸡长大开下蛋。其余鸡蛋全部出售。
 3)    每年2月份农夫会出售鸡龄大于一年的鸡总数的60%，剩余公鸡数至少占剩余总数的5%。*/
public class ChickenFactory implements AbstractFactory {
	
	Storage storage = Storage.getInstance();
	List<Chicken> smallHens = new ArrayList<Chicken>(); //小母鸡
	List<Chicken> smallCocks = new ArrayList<Chicken>();//小公鸡
	ArrayList<Chicken> chickenList = storage.getmChickenList();
	Object obj = new Object();
	
	/**
	 * 下单和孵化
	 */
	@Override
	public void produce(int year, int month) {
//		synchronized(obj) {
			Chicken chicken = new Chicken();
			//除了7,8,9三个月不下蛋,其余每月每天下单,2月份下完蛋再卖鸡
			if (MonthUtils.isRight(month)) {
//				obj.notify();
				//拿到下蛋母鸡的数量
				int chickenCount = chickenList.size();
				//判断闰年
				if (YearUtils.isLeapYear(year)) {
					long eggs = chickenCount * 9 * 274;
					chicken.setmEggs(eggs);	
				} else {
					long eggs = chickenCount * 9 * 273;
					chicken.setmEggs(eggs);	
				}
				/*try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			} else {
				/*obj.notify();
				try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
			//3月份21天孵化剩余蛋的20%
			if (MonthUtils.isThirdMonth(month)) {
//				obj.notify();
				//拿到剩余的总蛋数
				long eggs = chicken.getmEggs();
				//孵化20%的蛋
				long totalChicken = (long)(eggs * 0.2);
				Random r = new Random();
				//通过随机数计算生了多少只母鸡,多少只公鸡
				for (int i = 0; i < totalChicken; i++) {
					int num = r.nextInt(100) + 1;
					//生的小母鸡数量
					if (num % 2 == 0) {
						Chicken c = new Chicken();
						chicken.mAge = 0;
						chicken.mGender = "female";
						smallHens.add(c);
					} else {
						//公鸡数量
						Chicken c = new Chicken();
						chicken.mAge = 0;
						chicken.mGender = "male";
						smallCocks.add(c);
					}
				}
				/*try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			} else {
				/*obj.notify();
				try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
//			YearUtils.go(new ChickenFactory(), new Producer());
//		} 
	}

	/**
	 * 120天以后,小鸡开始下蛋
	 * @param month 月份
	 * @param day 月中的某一天
	 */
	public void daysAfter(int month, int day) {
		MonthUtils.daysAfter(month, day);
		chickenList.addAll(smallHens);
	}
	
	/**
	 * 一年以后,小鸡变成老鸡
	 * @param year 年份
	 */
	public void oneYearAfter(int year) {
		MonthUtils.oneYearAfter(year);
		//小母鸡小公鸡年龄变为1
		for (Chicken chicken : smallHens) {
			chicken.mAge = 1;
		}
		for (Chicken chicken : smallCocks) {
			chicken.mAge = 1;
		}
		//一年以后,小公鸡小母鸡长大变成老鸡
		chickenList.addAll(smallHens);
		smallHens.clear();
		chickenList.addAll(smallCocks);
		smallCocks.clear();
	}
	
	/**
	 * 2月份出售年龄大于1的老鸡
	 */
	@Override
	public void consume(int year, int month) {
//		synchronized (obj) {
			if (month == 2) {
//				obj.notify();
				List<Chicken> chickens = new ArrayList<>();
				chickens.addAll(chickenList);
				//需要被卖掉的鸡的总数
				long count = 0;
				//鸡的总数量
				long totalCount = chickenList.size();
				//卖之前公鸡的总数
				long sell_ago_cock = 0;
				//卖掉的公鸡的数量
				long sell_after_cock = 0;
				for (Chicken chicken : chickenList) {
					if ("male".equals(chicken.mGender)) {
						sell_ago_cock++;
					}
				}
				//卖掉的鸡的数量
				long sellCount = (long)(count * 0.6);
				//剩余数量
				long residueCount = totalCount - sellCount;
				//被卖掉的鸡
				chickens.subList(0, (int)residueCount).clear(); 
				//遍历被卖掉的鸡的集合
				for (Chicken chicken : chickens) {
					//统计被卖掉的公鸡数量
					if (chicken.mAge >= 1 && "male".equals(chicken.mGender)) {
						sell_after_cock++;
					}
				}
				//剩余总数的5%
				long residue_little_count= (long)(residueCount * 0.05);
				//剩余公鸡的数量
				long residule_cock_count = sell_ago_cock - sell_after_cock;
				//剩余公鸡的数量小于剩余总数的5%
				if (residule_cock_count < residue_little_count) {
					//卖掉40%的母鸡,20%的公鸡
					long totalHens = 0;
					long totalCock = 0;
					for (Chicken chicken : chickenList) {
						if ("female".equals(chicken.mGender) && chicken.mAge >= 1) {
							totalHens++;
						}
						if ("male".equals(chicken.mGender) && chicken.mAge >= 1) {
							totalCock++;
						}
					}
					long sellHens = (long)(totalHens * 0.4);
					long sellCocks = (long)(totalCock * 0.2);
					long sellTotalChicken = sellHens + sellCocks;
					chickenList.subList(0, (int)sellTotalChicken).clear();
				} else {
					//剩余公鸡的数量大于等于剩余总数的5%, 直接卖掉60%的鸡
					chickenList.subList(0, (int)sellCount).clear();
				}
				System.out.println("-----------------------------------------------------");
				System.out.println("第" + year +  "年鸡的总数:");
				int oldHensCount = 0;
				int oldCocksCount = 0;
				int smallHensCount = 0;
				int smallCocksCount = 0;
				for (Chicken chicken : chickenList) {
					if (chicken.mAge >= 1 && "female".equals(chicken.mGender)) {
						oldHensCount++;
					}
					if (chicken.mAge >= 1 && "male".equals(chicken.mGender)) {
						oldCocksCount++;
					}
					if (chicken.mAge < 1 && "female".equals(chicken.mGender)) {
						smallHensCount++;
					}
					if (chicken.mAge < 1 && "male".equals(chicken.mGender)) {
						smallCocksCount++;
					}
				}
				System.out.println("老母鸡的数量: " + oldHensCount + ", 老公鸡的数量: " + oldCocksCount
					+ ", 小母鸡的数量: " +smallHensCount + ", 小公鸡的数量: " + smallCocksCount);
				/*try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			} else {
				/*obj.notify();
				try {
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
//			YearUtils.go(new ChickenFactory(), new Consumer());
			//MonthUtils.timeChange(year, month);
//		}
	}

	@Override
	public int getFemaleNum() {
		return 0;
	}

	@Override
	public int getMaleNUm() {
		return 0;
	}

}
