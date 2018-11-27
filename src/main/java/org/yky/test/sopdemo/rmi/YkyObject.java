package org.yky.test.sopdemo.rmi;

import java.io.Serializable;

/**
 * Created by hp on 2018/10/28.
 */
public class YkyObject implements Serializable, Cloneable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
