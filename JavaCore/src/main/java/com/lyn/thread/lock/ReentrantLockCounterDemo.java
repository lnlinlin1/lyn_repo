package com.lyn.thread.lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounterDemo {

	public static void main(String[] args) {

		Lock counterLock = new ReentrantLock();
		
		Counter counter1 = new Counter(counterLock);
		Counter counter2 = new Counter(counterLock);
		
		final CounterRunnable runnable1 = new CounterRunnable(counter1);
		final CounterRunnable runnable2 = new CounterRunnable(counter2);
		
		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable2);
		thread1.setName("Thread1");
		thread2.setName("Thread2");
		
		Thread backgroundThread = new Thread(new Runnable(){
			@Override
			public void run() {

				try {
					System.out.println("sleep 10 sec");
					Thread.sleep(10000);
					System.out.println("going to stop the threads");
					runnable1.stop();
					runnable2.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		backgroundThread.setDaemon(true);
		
		thread1.start();
		thread2.start();
		backgroundThread.start();
		
		
	}
	
	static class Counter{
		private static int count;
		private Lock lock = null;
		public Counter(Lock lock){
			this.lock = lock;
		}
		public void increment(){
			try {
				lock.lock();
				System.out.println("["+Thread.currentThread().getName()+"]:Count="+(++count));
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				lock.unlock();
			}
		}
	}
	
	static class CounterRunnable implements Runnable{
		
		private Counter counter = null;
		private volatile AtomicBoolean isRunning = new AtomicBoolean(true);
		
		public CounterRunnable(Counter counter){
			this.counter = counter;
		}
		
		public void stop(){
			isRunning.set(false);
		}
		
		@Override
		public void run() {
			
			while(isRunning.get()){
				counter.increment();
			}
			
			System.out.println(Thread.currentThread().getName()+" is stopped!!");
			
		}
	}
	
}
