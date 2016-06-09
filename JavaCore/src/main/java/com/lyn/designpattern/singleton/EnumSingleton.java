package com.lyn.designpattern.singleton;

import java.util.Random;

/**
 * use enumeration to implement the singleton pattern
 * but the enumeration feature comes from jdk 1.5
 * @author lenovo
 *
 */
public enum EnumSingleton {

	INSTANCE;
	
	private EnumSingleton(){
	}
	
	public int getRandomNumber(){
		return new Random().nextInt(10);
	}
	
}
