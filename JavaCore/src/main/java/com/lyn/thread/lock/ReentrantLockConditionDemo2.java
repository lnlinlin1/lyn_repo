package com.lyn.thread.lock;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ReentrantLockConditionDemo2 {

	private static Lock lock = null;
	private static Condition fullCondition = null;
	private static Condition emptyCondition = null;
	
	static class ProducerThread extends Thread{
		
		private Queue<Integer> queue = null;
		private Random r = null;
		private volatile AtomicBoolean isRunning = new AtomicBoolean(true);
		private int maxSize;
		
		public void stopThread(){
			isRunning.set(false);
		}
		
		public ProducerThread(String threadName, Queue<Integer> queue, int maxSize){
			super(threadName);
			this.queue = queue;
			r = new Random();
			this.maxSize = maxSize;
		}
		
		@Override
		public void run() {
			while(isRunning.get()){
				produceTheNumber();
			}
		}

		private void produceTheNumber() {
			try {
				lock.lock();
				while(queue.size() == maxSize){
					fullCondition.await();
				}
				queue.add(r.nextInt(100));
				emptyCondition.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
	static class ConsumerThread extends Thread{
		@Override
		public void run() {
			try {
				lock.lock();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
}
