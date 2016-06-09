package com.lyn.designpattern.singleton;

/**
 * 
 * @author lenovo
 *
 */
public class InnerClassSingleton {

	private static class InstanceHolder{
		static final InnerClassSingleton instance = new InnerClassSingleton();
	}
	
	private InnerClassSingleton(){}
	
	public InnerClassSingleton getInstance(){
		return InstanceHolder.instance;
	}
	
}
