package com.cn.thread;

import java.util.concurrent.locks.Lock;

import org.junit.Test;

public class TestTwinsLock{
	private  static volatile int j =0;
	public static void main(String[] args) {
		final Lock lock = new TwinsLock();
		
		class Worker extends Thread{
			@Override
			public void run() {
				while(true){
					lock.lock();
					System.err.println("取到锁线程： "+Thread.currentThread().getName());
					try {
						System.err.println(Thread.currentThread().getName()+"@@");
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}finally{
						
						lock.unlock();System.out.println("释放锁： "+Thread.currentThread().getName());
					}
				}
			}
		}
			for(int i=0;i<10 ;i++){
				Worker worker = new Worker();
				worker.setDaemon(true);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				worker.start();
			}
			// 每隔1秒换行
		}
		
}
