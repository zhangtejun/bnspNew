package com.cn.uuu.controller;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * CGLib采用非常底层的字节码技术，可以为一个类创建子类，并在子类中采用方法拦截的结束拦截所有父类方法的调用，
 * 并顺势织入横切逻辑。我们采用CGLib技术可以编写一个可以为任何类创建织入横切逻辑代理对象的代理创建器，
 * 下面看一个使用CGLib代理技术实现横切的一个例子：
 * @author 10539
 *
 */
public class JDKProxyTestCG {
	public void removeUser(int userId) {
        System.out.println("模拟删除用户：" + userId);
    }

    public void addUser(int userId) {
        // TODO Auto-generated method stub
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        JDKProxyTestCG userService =(JDKProxyTestCG)proxy.getProxy(JDKProxyTestCG.class);
        userService.removeUser(7);
    }
}

class CglibProxy implements MethodInterceptor {

    // private static CglibProxy proxy = new CglibProxy();
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);// 设置需要创建子类的类
        enhancer.setCallback(this);//回调方法的参数为代理类对象CglibProxy，最后增强目标类调用的是代理类对象CglibProxy中的intercept方法 
        return enhancer.create();// 通过字节码技术动态创建子类实例
    }
    /**
     * 用户通过getProxy(Class clazz)为一个类创建动态代理对象，
     * 该代理对象通过扩展clazz创建代理对象，在这个代理对象中，我们织入横切逻辑代码。
     * intercept是CGLib定义的Interceptor接口的方法，它拦截所有目标方法的调用，
     * 参数arg0表示目标类的实例；参数arg1表示目标类方法的反射对象；
     * arg2表示目标类方法的参数的反射对象；arg3表示代理类实例
     */
    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2,
            MethodProxy arg3) throws Throwable {
//        PerformanceMonitor.begin(arg0.getClass().getName() + "."
//                + arg1.getName());
    	System.err.println("###########");
        Object result = arg3.invokeSuper(arg0, arg2);
//        PerformanceMonitor.end();
        return result;
    }


}