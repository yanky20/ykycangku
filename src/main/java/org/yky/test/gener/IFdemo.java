package org.yky.test.gener;

/**
 * Created by hp on 2018/3/26.
 */
public interface IFdemo<T> {

    //public void writeName(T t, String name);

    public void write(T t);

    public T get();
}
