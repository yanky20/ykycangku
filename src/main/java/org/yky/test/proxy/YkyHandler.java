package org.yky.test.proxy;

import org.yky.test.anno.YkyAno;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hp on 2018/8/8.
 */
public class YkyHandler implements InvocationHandler {
    @YkyAno(name = "2")
    private Yky yky;
    public YkyHandler setObject(Yky yky) {
        this.yky = yky;
        return this;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(1);
        System.out.println(proxy.getClass().getName());
        method.invoke(yky, args);
        System.out.println(2);
        return null;
    }
}
