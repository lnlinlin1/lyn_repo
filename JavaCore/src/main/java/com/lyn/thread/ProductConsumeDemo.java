package com.lyn.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * point!!!
 * 1, you can use wait() and notifiy() to communicate among the threads (>3)
 * 2, always use wait() and notify() in the synchonized block, or JVM will throw IllegalMonitorStateException
 * 3, always use wait() in the while condition not if condition
 * 4, always use the wait() on the shared object (e.g. queue)
 * 5, notifyAll() is better than notify()
 * 
 * 
 * Producer-Consumer Pattern
 * Consumer
 * synchronized(obj){
 *    while(!workToDo){
 *       obj.wait()
 *    }
 *    //get next item from queue
 *    workToDo = false;
 * }
 * // do work on the item
 * 
 * Producer
 * synchronized(obj){
 *    if/while(!workToDo){
 *    	//add work to queue
 *       workToDo = false
 *    }
 *    obj.notifyAll()
 * }
 * 
 * 
 * 
 * @author lenovo
 *
 */
public class ProductConsumeDemo {

	public static void main(String[] args) throws Exception{
		
		  System.out.println("How to use wait and notify method in Java"); 
		  System.out.println("Solving Producer Consumper Problem"); 
		  Queue<Integer> buffer = new LinkedList<Integer>();
		  int maxSize = 10; 
		  Thread producer = new Producer(buffer, maxSize, "PRODUCER"); 
		  Thread consumer = new Consumer(buffer, maxSize, "CONSUMER"); 
		  producer.start(); consumer.start(); 
		
	}
	
	static class Producer extends Thread{
		private Queue<Integer> queue;
		private int maxSize;
		public Producer(Queue<Integer> queue, int maxSize, String name){
			super(name);
			this.queue = queue;
			this.maxSize = maxSize;
		}
		
		@Override
		public void run() {
			Random r = new Random();
			while(true){
				synchronized(queue){
					while(queue.size()==maxSize){
						try {
							System.out .println("Queue is full, " + "Producer thread waiting for " + "consumer to take something from queue"); 
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int i = r.nextInt(100);
					queue.add(i);
					queue.notifyAll();
					System.out.println("Producing value : " + i);
				}
				try {
					sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	static class Consumer extends Thread{
		private Queue<Integer> queue;
		private volatile AtomicBoolean isRunning = new AtomicBoolean(true);
		public Consumer(Queue<Integer> queue, int maxSize, String name){
			super("name");
			this.queue = queue;
		}
		
		@Override
		public void run() {
			while(isRunning.get()){
				synchronized(queue){
					while(queue.isEmpty()){
						System.out.println("Queue is empty," + "Consumer thread is waiting" + " for producer thread to put something in queue");
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("Consuming value : " + queue.remove());
					queue.notifyAll(); 
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
