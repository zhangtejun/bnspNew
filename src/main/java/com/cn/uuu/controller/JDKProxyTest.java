package com.cn.uuu.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyTest {
	public static void main(String[] args) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 这里有两种写法，我们采用略微复杂的一种写法，这样更有助于大家理解。
		// Class<?> proxyClass=
		// Proxy.getProxyClass(JDKProxyTest.class.getClassLoader(),HelloWorld.class);
		// final Constructor<?> cons =
		// proxyClass.getConstructor(InvocationHandler.class);
		// final InvocationHandler ih = new MyInvocationHandler(new
		// HelloWorldImpl());
		// HelloWorld helloWorld= (HelloWorld)cons.newInstance(ih);
		// helloWorld.sayHello();

		// 下面是更简单的一种写法，本质上和上面是一样的
		HelloWorld helloWorld = (HelloWorld) Proxy.newProxyInstance(
				JDKProxyTest.class.getClassLoader(),
				new Class<?>[] { HelloWorld.class }, new MyInvocationHandler(
						new HelloWorldImpl()));
		helloWorld.sayHello();
		// 3.详细说明
		HelloWorldImpl hli = new HelloWorldImpl(); // 在这里指定被代理类
		// 通过被代理类对象声明一个代理类对象
		InvocationHandler mih = new MyInvocationHandler(hli);
		Class<?> cls = mih.getClass();
		// 以下是一次性生成代理类实例:
		// 返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序。
		HelloWorld hello = (HelloWorld) Proxy.newProxyInstance(
				cls.getClassLoader(), // 定义类加载器
				hli.getClass().getInterfaces(), // 实现的接口列表
				mih); // 指派方法调用的调用处理程序:InvocationHandler对象

		hello.sayHello(); // 调用代理处理器上的方法
	}
}

/**
 * 代理Handler
 * 
 * @author 10539
 * 
 */
class MyInvocationHandler implements InvocationHandler {

	private Object target;// 目标对象

	/**
	 * 构造方法,初始化被代理对象
	 * 
	 * @param target 是被代理的对象
	 */
	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	/**
	 * @param proxy 指代理类
	 * @param method 被代理的方法
	 * @param args 被代理的方法所需要的参数数组
	 * @return 在代理实例上处理方法调用并返回结果。
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("代理前执行的操作!");
		method.invoke(target, args);
		System.out.println("代理后执行的操作!");
		return null;
	}

}

class HelloWorldImpl implements HelloWorld {

	@Override
	public void sayHello() {
		System.err.println("HelloWorldImpl ----> sayHello()");
	}

}

interface HelloWorld {
	public void sayHello();

	static int aa = 0;
	// abstract void bb();
}