package com.lyn.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * this demo is a solution for deadlock of re-entrant lock when using synchronized keyword.
 * ReentrantLock solution (com.lyn.thread.SynchronziedDeadLockDemo)
 * @author lz83482
 *
 */
public class ReentrantThreadDemo {

	public static void main(String[] args) {
		
		TestDeadLock td1 = new TestDeadLock();
		TestDeadLock td2 = new TestDeadLock();
		
		td1.flag = 1;
		td2.flag = 0;
		
		Thread t1 = new Thread(td1);
		Thread t2 = new Thread(td2);
		t1.start();
		t2.start();
		
	}
	
}

class TestDeadLock implements Runnable{
	
	public int flag;
	static Lock lock1 = new ReentrantLock();
	static Lock lock2 = new ReentrantLock();
	
	@Override
	public void run() {

		System.out.println("flag="+flag);
		if(flag == 0){
			try {
				lock1.lock();
				Thread.sleep(500);
				getLock2();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock1.unlock();
			}
		}
		
		if(flag == 1){
			try {
				lock2.lock();
				Thread.sleep(500);
				getLock1();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock2.unlock();
			}
		}
		
	}

	private void getLock1() {
		boolean captured = false;
		try {
			captured = lock1.tryLock(2,TimeUnit.SECONDS);
			if(captured){
				System.out.println("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(captured){
				lock1.unlock();
			}
		}
	}

	private void getLock2() {
		boolean captured = false;
		try {
			captured = lock2.tryLock(5, TimeUnit.SECONDS);
			if(captured){
				System.out.println("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(captured){
				lock2.unlock();
			}
		}
	}
}