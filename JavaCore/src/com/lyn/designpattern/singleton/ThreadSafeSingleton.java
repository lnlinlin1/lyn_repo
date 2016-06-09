package com.lyn.designpattern.singleton;

/**
 * two lock-check lazy singleton
 * solve the thread unsafe problem
 * @author lenovo
 *
 */
public class ThreadSafeSingleton {

	private volatile static ThreadSafeSingleton instance = null;
	
	private ThreadSafeSingleton(){}
	
	public static ThreadSafeSingleton getInstance(){
		if(instance == null){
			synchronized(ThreadSafeSingleton.class){
				if (instance == null){
					instance = new ThreadSafeSingleton();
				}
			}
		}
		return instance;
	}
	
}
