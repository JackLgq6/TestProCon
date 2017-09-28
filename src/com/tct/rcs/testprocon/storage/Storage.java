package com.tct.rcs.testprocon.storage;

import java.util.ArrayList;

import com.tct.rcs.testprocon.bean.Cattle;
import com.tct.rcs.testprocon.bean.Chicken;
import com.tct.rcs.testprocon.bean.Sheep;

/**
奶牛：
1)    公牛和母牛各20头，牛龄2岁，农夫每年8月为母牛配种， 280天之后下崽。
2)    每只母牛每次只能产仔1头，小牛的性别随机，小牛2年会后开始生产，。
3)    每年2月份农夫会出售牛龄大于2岁的成年牛总数的40%。剩余公牛至少占总数10%.

现在是2017年9月1日，请输出如下数据：
1.     请问12年后农场3类动物的剩余数量各是多少，且需要罗列出成年和未成年雌性雄性的数量，以及鸡蛋的数量。同时请注意闰年天数。
2.     需要题目中标黄的数字可以动态调节。
3.     支持可以输出未来随机一天农场上面的各种动物大小雌雄的数量。
 *
 *
 */
public class Storage {
	
    private ArrayList<Cattle> mCattleList;
    private ArrayList<Chicken> mChickenList;
    private ArrayList<Sheep> mSheepList;
	
	
    public ArrayList<Cattle> getmCattleList() {
		return mCattleList;
	}

	public void setmCattleList(ArrayList<Cattle> mCattleList) {
		this.mCattleList = mCattleList;
	}

	public ArrayList<Chicken> getmChickenList() {
		return mChickenList;
	}

	public void setmChickenList(ArrayList<Chicken> mChickenList) {
		this.mChickenList = mChickenList;
	}

	public ArrayList<Sheep> getmSheepList() {
		return mSheepList;
	}

	public void setmSheepList(ArrayList<Sheep> mSheepList) {
		this.mSheepList = mSheepList;
	}

	/**
     * Chicken库存实例
     */
    private static Storage instance;
    
    private Storage(){}
    
    /**
     * 获取单例
     * @return
     */
    public static Storage getInstance(){
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }
    
    
    
    
}
