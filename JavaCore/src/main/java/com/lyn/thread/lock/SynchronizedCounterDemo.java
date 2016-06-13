package com.lyn.thread.lock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SynchronizedCounterDemo {
	
	public static void main(String[] args) {
		
		Counter counter1 = new Counter();
		Counter counter2 = new Counter();
		
		final CounterRunnable runnable1 = new CounterRunnable(counter1);
		final CounterRunnable runnable2 = new CounterRunnable(counter2);
		
		Thread thread1 = new Thread(runnable1);
		thread1.setName("Thread1");
		Thread thread2 = new Thread(runnable2);
		thread2.setName("Thread2");
		
		thread1.start();
		thread2.start();
		
		Thread backgroundThread = new Thread(new Runnable(){
			@Override
			public void run() {
				sleepFor10Sec();
				stopRunningThread();
			}

			private void sleepFor10Sec() {
				System.out.println("thread sleep for 10 sec");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("sleep complete, going to stop the threads");
			}

			private void stopRunningThread() {
				runnable1.stop();
				runnable2.stop();
			}
		});
		backgroundThread.setDaemon(true);
		backgroundThread.start();
		
	}
	
	static class Counter{
		private static int count = 0;
		public synchronized void increment(){
			System.out.println("["+Thread.currentThread().getName()+"]:count="+(++count));
		}
	}
	
	static class CounterRunnable implements Runnable{
		private Counter counter = null;
		private volatile AtomicBoolean isRunning = new AtomicBoolean(true);
		
		public void stop(){
			isRunning.set(false);
		}
		
		public CounterRunnable(Counter counter){
			this.counter = counter;
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
