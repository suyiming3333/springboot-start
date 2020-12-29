package com.corn.springboot.start.proxy;


import java.lang.reflect.Method;

interface TestInterface{
    void doSomething();
}

interface InvocationHadler{
    //需要代理的对象、方法、参数
    Object invoke(Object target, Method method, Object... args);
}

public class ManualProxyDemo {

    public static void main(String[] args) {
        TestInterface proxyInstance = createProxyInstance(new InvocationHadler() {
            @Override
            public Object invoke(Object target, Method method, Object... args) {
                System.out.println("ready to do something");
                return null;
            }
        }, TestInterface.class);

        proxyInstance.doSomething();
    }

    public static TestInterface createProxyInstance(InvocationHadler invocationHadler,Class<?> clazz){
        //返回一个代理对象
        return new TestInterface() {
            @Override
            public void doSomething() {
                try {
                    Method method = clazz.getMethod("doSomething");
                    //调用当前new实例的method
                    invocationHadler.invoke(this,method);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
