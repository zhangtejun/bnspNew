package com.cn.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadUtils {
	// 保证所有ConnectionRunner能够同时开始
	static CountDownLatch start = new CountDownLatch(1);
	// main线程将会等待所有ConnectionRunner结束后才能继续执行
	static CountDownLatch end;
}
