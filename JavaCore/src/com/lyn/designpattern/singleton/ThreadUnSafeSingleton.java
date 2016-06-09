package com.lyn.designpattern.singleton;

/**
 * this is not thread safe when invoking the getInstance() method.
 * @author lenovo
 *
 */
public class ThreadUnSafeSingleton {

	private static ThreadUnSafeSingleton instance = null;
	
	private ThreadUnSafeSingleton(){}
	
	public static ThreadUnSafeSingleton getInstance(){
		if(instance == null){
			instance = new ThreadUnSafeSingleton();
		}
		return instance;
	}
	
}
