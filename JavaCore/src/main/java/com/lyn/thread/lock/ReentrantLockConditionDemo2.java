package com.lyn.thread.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionDemo2 {

	private static Lock lock = null;
	private static Condition fullCondition = null;
	private static Condition emptyCondition = null;
	
	public static void main(String[] args) {
		lock = new ReentrantLock();
		fullCondition = lock.newCondition();
		emptyCondition = lock.newCondition();
		
		Queue<Integer> queue = new LinkedList<Integer>();
		
		final ProducerThread producer = new ProducerThread("ProducerThread", queue, 10);
		final ConsumerThread consumer = new ConsumerThread("ConsumerThread", queue);
		
		Thread backgroundThread = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				producer.stopThread();
				consumer.stopThread();
			}
		});
		backgroundThread.setDaemon(true);
		
		producer.start();
		consumer.start();
		backgroundThread.start();
		
	}
	
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
			System.out.println("Producer Thread is stopped!!");
		}

		private void produceTheNumber() {
			try {
				lock.lock();
				while(queue.size() == maxSize){
					System.out.println("queue is full, cannot produce anymore");
					fullCondition.await();
				}
				int num = r.nextInt(100);
				queue.add(num);
				System.out.println("[ConsumerThread] add number "+num);
				emptyCondition.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
	static class ConsumerThread extends Thread{
		
		private Queue<Integer> queue;
		private volatile AtomicBoolean isRunning = new AtomicBoolean(true);
		
		public ConsumerThread(String threadName, Queue<Integer> queue){
			super(threadName);
			this.queue = queue;
		}
		
		public void stopThread(){
			isRunning.set(false);
		}
		
		@Override
		public void run() {
			while(isRunning.get()){
				consumeTheNumber();
			}
			System.out.println("Consumer Thread is stopped!!");
		}

		private void consumeTheNumber() {

			try {
				lock.lock();
				while(queue.size() == 0){
					System.out.println("queue is empty, cannot get number anymore");
					emptyCondition.await();
				}
				System.out.println("[ProducerThread] get number "+queue.remove());
				fullCondition.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			
		}
	}
	
}
