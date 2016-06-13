package com.lyn.thread.threadlocal;

import org.junit.Test;

public class ThreadLocalDemoTest {

	@Test
	public void test() {
		
		Thread t1 = new TestThread("t1");
		Thread t2 = new TestThread("t2");
		Thread t3 = new TestThread("t3");
		t1.start();
		t2.start();
		t3.start();
		
	}
	
	public static void main(String[] args) {
		new ThreadLocalDemoTest().test();
	}
	
	class TestThread extends Thread{
		
		public TestThread(String threadName) {
			super(threadName);
		}
		
		@Override
		public void run(){
			for(int i=0;i<3;i++){
				System.out.println("Thread["+Thread.currentThread().getName()+"],counter="+ThreadLocalDemo.next());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
