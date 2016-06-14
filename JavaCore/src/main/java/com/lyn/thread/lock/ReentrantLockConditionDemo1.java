package com.lyn.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * two threads A B, first A prints 1, 2, 3, then B prints 4, 5, 6, finally A prints 7, 8, 9
 * @author lz83482
 *
 */
public class ReentrantLockConditionDemo1 {

	private static Lock lock = null;
	private static Condition reach3Condition = null;
	private static Condition reach6Condition = null;
	
	static{
		lock = new ReentrantLock();
		reach3Condition = lock.newCondition();
		reach6Condition = lock.newCondition();
	}
	
	public static void main(String[] args) {
		ThreadA threadA = new ThreadA("ThreadA");
		ThreadB threadB = new ThreadB("ThreadB");
		threadA.start();
		threadB.start();
	}
	
	static class ThreadA extends Thread{
		
		public ThreadA(String threadName){
			super(threadName);
		}
		
		@Override
		public void run() {
			try{
				lock.lock();
				while(Num.i<=3){
					System.out.println("["+Thread.currentThread().getName()+"]:"+Num.i++);
				}
				reach3Condition.signal();
			}finally{
				lock.unlock();
			}
			
			try {
				lock.lock();
				reach6Condition.await();
				while(Num.i<=9){
					System.out.println("["+Thread.currentThread().getName()+"]:"+Num.i++);
				}
				
			}catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			
		}
	}
	
	static class ThreadB extends Thread{
		
		public ThreadB(String threadName){
			super(threadName);
		}
		
		@Override
		public void run() {
			try {
				lock.lock();
				while(Num.i<=3){
					reach3Condition.await();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				lock.unlock();
			}
			try {
				lock.lock();
				while(Num.i<=6){
					System.out.println("["+Thread.currentThread().getName()+"]:"+Num.i++);
				}
				reach6Condition.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
	static class Num{
		public static int i=1;
	}
	
}
