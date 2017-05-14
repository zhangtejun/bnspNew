package com.cn.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独占锁就是在同一时刻只能有一个线程获取到锁，而其他获取锁的线程只能处于同步队列中等待，
 * 只有获取锁的线程释放了锁，后继的线程才能够获取锁
 * 
 * @author 10539
 * 
 */
class Mutex implements Lock {
	// 静态内部类，自定义同步器.该内部类继承了同步器并实现了独占式获取和释放同步状态。
	private static class Sync extends AbstractQueuedSynchronizer {
		// 1.是否处于占用状态
		/**
		 * 当前同步器是否在独占模式下被线程占用，一般该方法表示是否被当前线程所独占。
		 */
		@Override
		protected boolean isHeldExclusively() {
			// 获取当前同步状态
			return getState() == 1;
		}

		// 2.当状态为0的时候获取锁
		/**
		 * tryAcquire():独占式获取同步状态，实现该方法需要查询当前状态并判断同步状态是否符合预期，然后再进行CAS设置同步状态
		 */
		@Override
		public boolean tryAcquire(int acquires) {
			// 使用CAS设置当前状态，该方法能够保证状态设置的原子性。
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		// 3.释放锁，将状态设置为0
		/**
		 * 独占式释放同步状态，等待获取同步状态的线程将有机会获取同步状态。
		 */
		protected boolean tryRelease(int releases) {
			if (getState() == 0)
				throw new IllegalMonitorStateException();
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		// 4.返回一个Condition，每个condition都包含了一个condition队列
		Condition newCondition() { return new ConditionObject();}
	}

	// 5.仅需要将操作代理到Sync上即可
	private final Sync sync = new Sync();
	
	@Override
	public void lock() {
		//独占式获取同步状态，如果当前线程获取同步状态成功，则由该方法返回，否则将会进入同步队列等待，该方法将会调用重写的tryAcquire()
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(1); 
	}
	public boolean isLocked() { return sync.isHeldExclusively(); }
	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

}
