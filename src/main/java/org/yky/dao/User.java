package org.yky.dao;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 2120869894112984147L;

    private int uid;
    private String name;
    private int sex;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}