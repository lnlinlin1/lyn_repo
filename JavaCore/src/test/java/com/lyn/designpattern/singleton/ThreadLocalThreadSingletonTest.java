package com.lyn.designpattern.singleton;


import org.junit.Test;

public class ThreadLocalThreadSingletonTest {

	@Test
	public void getInstance() {
		
		final ThreadLocalThreadSingleton threadSingleton = ThreadLocalThreadSingleton.getInstance();
		
		new Thread(new Runnable(){
			@Override
			public void run() {

				ThreadLocalThreadSingleton threadSingleton1 = ThreadLocalThreadSingleton.getInstance();
				assert threadSingleton != threadSingleton1;
				
			}
		}).start();
		
	}

}
