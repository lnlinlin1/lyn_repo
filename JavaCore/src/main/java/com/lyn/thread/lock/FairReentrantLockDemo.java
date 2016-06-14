package com.lyn.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class FairReentrantLockDemo {

	public static void main(String[] args) {
		
		final ReentrantLock fairLock = new ReentrantLock(true);
		Thread lock1 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					fairLock.lock();
					Thread.sleep(10000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					fairLock.unlock();
				}
			}
		});
		
		Thread lock2 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					fairLock.lock();
					System.out.println("this is thread 2");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					fairLock.unlock();
				}
			}
		});
		
		Thread lock3 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					fairLock.lock();
					System.out.println("this is thread 3");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					fairLock.unlock();
				}
			}
		});
		
		Thread lock4 = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					fairLock.lock();
					System.out.println("this is thread 4");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					fairLock.unlock();
				}
			}
		});
		
		try {
			lock1.start();
			Thread.sleep(500);
			lock2.start();
			Thread.sleep(500);
			lock3.start();
			Thread.sleep(500);
			lock4.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
