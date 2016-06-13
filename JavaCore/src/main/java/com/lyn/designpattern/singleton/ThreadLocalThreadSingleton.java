package com.lyn.designpattern.singleton;

/**
 * use the thread locale feature
 * each thread will get a separate instance
 * 
 * IT IS NOT THE SINGLETON PATTERN EXAMPLE!!!!
 * 
 * @author lenovo
 *
 */
public class ThreadLocalThreadSingleton {
	
	private static ThreadLocal<ThreadLocalThreadSingleton> threadLocal = new ThreadLocal<ThreadLocalThreadSingleton>();
	
	private ThreadLocalThreadSingleton(){}
	
	public static ThreadLocalThreadSingleton getInstance(){
		
		if(threadLocal.get() == null){
			threadLocal.set(new ThreadLocalThreadSingleton());
		}
		return threadLocal.get();
		
	}
	
}
