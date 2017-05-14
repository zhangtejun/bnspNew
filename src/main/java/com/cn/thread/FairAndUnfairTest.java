package com.cn.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class FairAndUnfairTest {
	/**
	 * 创建策略是fair的 ReentrantLock。fair为true表示是公平锁，fair为false表示是非公平锁。
	 */
	private static Lock fairLock = new ReentrantLock2(true);
	private static Lock unfairLock = new ReentrantLock2(false);
	
	// 保证所有ConnectionRunner能够同时开始
	static CountDownLatch start = new CountDownLatch(1);
	// main线程将会等待所有ConnectionRunner结束后才能继续执行
	static CountDownLatch end = new CountDownLatch(5);
	
	@Test
	public void fair() {
		testLock(fairLock);
	}

	@Test
	public void unfair() {
		testLock(unfairLock);
	}

	private void testLock(Lock lock)  {
		// 启动5个Job（略）
		for (int i = 0; i < 5; i++) {
			Job job = new Job(lock);
			job.setDaemon(true);
			job.start();
		}
		start.countDown();
		try {
			end.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class Job extends Thread {
		
		
		
		private Lock lock;

		public Job(Lock lock) {
			this.lock = lock;
		}

		public void run() {
			
			try {
				start.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			lock.lock();
			try {
//				Thread.sleep(100);
				// 连续2次打印当前的Thread和等待队列中的Thread（略）
				System.err.println("Lock by Thread: "+Thread.currentThread().getName()+""
						+ "  waiting by : "+((ReentrantLock2)lock).getQueuedThreads()+" queue length :"+((ReentrantLock2)lock).getQueueLength());
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			end.countDown();
		}
	}

	private static class ReentrantLock2 extends ReentrantLock {
		public ReentrantLock2(boolean fair) {
			super(fair);
		}
		/**
		 * 该方法返回正在等待获取锁的线程列表，由于列表是逆序输出，为了方便观察结果，将其进行反转
		 */
		public Collection<Thread> getQueuedThreads() {
			List<Thread> arrayList = new ArrayList<Thread>(
					super.getQueuedThreads());
			Collections.reverse(arrayList);
			return arrayList;
		}
		public void name() {
			
		}
	}
}
