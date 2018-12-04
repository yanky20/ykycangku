package org.yky.alltest;


import net.sf.json.JSONObject;

/**
 * Created by lenovo on 2018/12/4.
 */
public class Main {
    public static void main(String[] args){
        Object1 o1;
        Object2 o2 = new Object2();
        o2.setAge(1);
        o2.setHobby("唱歌");
        o2.setName("yankaiyang");
        o2.setSex(2);
        o1 = (Object1) JSONObject.toBean(JSONObject.fromObject(o2), Object1.class);
        System.out.println(o1.getAge());
    }
}
