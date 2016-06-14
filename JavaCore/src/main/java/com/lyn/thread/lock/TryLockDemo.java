package com.lyn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * boolean lock.tryLock()
 * if failed to get the lock, (the other thread has not finished the synchronized block), it will return false
 * if succeed to get the lock, it will return true
 * 
 * lock.lock() // if another thread is holding the lock, the current thread will block here to wait the lock release.
 * 
 * new ReentrantLock() // default is false, un-fair lock
 * new ReentrantLock(true) // fair lock
 * @author lz83482
 *
 */
public class TryLockDemo {

	public static void main(String[] args) {
		
		final Lock lock = new ReentrantLock();
		
		Thread getLockThread = new Thread(new Runnable(){
			@Override
			public void run() {

				while(!lock.tryLock()){
					System.out.println("failed to get lock, sleep 3 sec");
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("got the lock successfully!!!");
				
			}
		});
		Thread setLockThread = new Thread(new Runnable(){
			@Override
			public void run() {

				try {
					lock.lock();
					Thread.sleep(10000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
				
			}
		});
		setLockThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getLockThread.start();
		
	}
	
}
