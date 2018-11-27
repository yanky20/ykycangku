package org.yky.test.proxy;

import java.lang.reflect.*;

/**
 * Created by hp on 2018/8/8. 动态代理对象因为已经默认继承了Proxy类，所以无法直接继承Yky类，从而无法转型为Yky类并获取Yky的方法并通过反射调用，因此
 * 只能退而求其次的实现Yky所有实现的接口，通过多态的方式调用并实现Yky重写某一个接口的方法，因此动态代理的实际被代理对象必须有实现接口;
 * 动态代理实际上实现的是代理对象与被代理对象对应关系的解耦与乱序组合，代理对象不再需要写死代码来调用代理对象的方法
 */
public class Yky implements YkyInterface, YkyInterface1 {


    public void a() {
        System.out.println("a");
    }

    public void b(int i) {
        System.out.println("b");
    }

    public static void main(String[] args){
        Yky yky = new Yky();
        Yky yky2 = new Yky();
        YkyHandler yh = new YkyHandler().setObject(yky);
        Class<?>[] ca = {YkyInterface.class};
        Class<?>[] ca1 = {YkyInterface1.class};
        //YkyInterface pyh = (YkyInterface) Proxy.newProxyInstance(yh.getClass().getClassLoader(), ca, yh);
        ((YkyInterface1) Proxy.newProxyInstance(yh.getClass().getClassLoader(), ca1, yh)).a();
        ((YkyInterface) Proxy.newProxyInstance(yh.getClass().getClassLoader(), ca, yh)).b(1);
    }
}
