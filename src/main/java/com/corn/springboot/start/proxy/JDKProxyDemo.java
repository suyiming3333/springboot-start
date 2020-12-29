package com.corn.springboot.start.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface HumanBehaveInterface {
    void sayHello(String name);
}

class MyInvocationHandler implements InvocationHandler {

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke");
        method.invoke(target, args);
        System.out.println("after invoke");
        return null;
    }
}

public class JDKProxyDemo {

    public static void main(String[] args) {
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(new HumanBehaveInterface() {
            @Override
            public void sayHello(String name) {
                System.out.println("hello:" + name);
            }
        });

        HumanBehaveInterface instance = (HumanBehaveInterface) Proxy.newProxyInstance(
                JDKProxyDemo.class.getClassLoader(),
                new Class<?>[]{HumanBehaveInterface.class},
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        return new HumanBehaveInterface() {
//                            @Override
//                            public void sayHello(String name) {
//                                System.out.println("hello:" + name);
//                            }
//                        };
//                    }
//                }
                myInvocationHandler
                );

        instance.sayHello("corn");
    }


}
