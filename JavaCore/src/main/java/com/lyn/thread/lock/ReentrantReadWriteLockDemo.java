package com.lyn.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

	public static void main(String[] args) {
		
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		int[] array = new int[]{-1,2,3,4};
		
		// get can multiple read lock at the same time
//		testReadLock(lock, array);
		
		// write lock will exclude all the lock read and write
		testWriteLock(lock, array);
	}

	private static void testWriteLock(ReentrantReadWriteLock lock, int[] array) {
		SetThread thread1 = new SetThread("SetThread1", array, lock);
		SetThread thread2 = new SetThread("SetThread2", array, lock);
		GetThread thread3 = new GetThread("GetThread1", array, lock);
		GetThread thread4 = new GetThread("GetThread2", array, lock);
		thread1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start();
		thread3.start();
		thread4.start();
	}

	private static void testReadLock(ReentrantReadWriteLock lock, int[] array) {
		GetThread thread1 = new GetThread("GetThread1", array, lock);
		GetThread thread2 = new GetThread("GetThread2", array, lock);
		thread1.start();
		thread2.start();
	}

}

class GetThread extends Thread{
	
	private int[] array;
	private ReentrantReadWriteLock lock;
	
	public GetThread(String threadName, int[] array, ReentrantReadWriteLock lock){
		super(threadName);
		this.array = array;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			lock.readLock().lock();
			System.out.println("["+Thread.currentThread().getName()+"] get from "+array[0]);
			Thread.sleep(2000);
			System.out.println("["+Thread.currentThread().getName()+"] stop");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.readLock().unlock();
		}
	}
	
}

class SetThread extends Thread{
	private int[] array;
	private ReentrantReadWriteLock lock;
	private Random r = new Random();
	public SetThread(String threadName, int[] array, ReentrantReadWriteLock lock){
		super(threadName);
		this.array = array;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			lock.writeLock().lock();
			array[0] = r.nextInt(10);
			System.out.println("["+Thread.currentThread().getName()+"]set to array[0]="+array[0]);
			Thread.sleep(5000);
			System.out.println("["+Thread.currentThread().getName()+"] stop");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
	}
}
