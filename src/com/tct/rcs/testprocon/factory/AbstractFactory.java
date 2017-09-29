package com.tct.rcs.testprocon.factory;


public interface AbstractFactory {
	
	public abstract void produce(int year, int month, int day);
	
	public abstract void consume(int year, int month);
	
	public abstract int getFemaleNum();
	
	public abstract int getMaleNUm();
	
}
