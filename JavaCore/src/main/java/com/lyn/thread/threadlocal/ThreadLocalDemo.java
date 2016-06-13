package com.lyn.thread.threadlocal;

/**
 * 
 * @author lenovo
 *
 */
public class ThreadLocalDemo {

	public static ThreadLocal<Integer> counterContext = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 10;
		}
	};
	
	public static int next(){
		counterContext.set(counterContext.get()+1);
		return counterContext.get();
	}
	
}
