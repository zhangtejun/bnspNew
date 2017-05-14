package com.cn.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.junit.Test;

public class TwinsLock implements Lock {
	private final Sync sync = new Sync(2);
	private static final class Sync extends AbstractQueuedSynchronizer {
		public Sync(int count) {
			if (count <= 0) {
				throw new IllegalArgumentException("count must large than zero.");
				}
				setState(count);
		}
		@Override
		protected int tryAcquireShared(int arg) {
			for(;;){
				int current = getState();
				int newCurrent = current - arg;
				if(newCurrent<0|| compareAndSetState(current, newCurrent)){
					return newCurrent;
				}
				
			}
		}
		
		@Override
		protected boolean tryReleaseShared(int arg) {
			for(;;){
				int current = getState();
				int newCurrent = current + arg;
				if(compareAndSetState(current, newCurrent)){
					return true;
				}
			}
		}
	}

	@Override
	public void lock() {
		sync.acquireShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}



